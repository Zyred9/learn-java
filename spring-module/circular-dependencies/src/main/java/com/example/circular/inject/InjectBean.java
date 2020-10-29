package com.example.circular.inject;

import com.example.circular.Assert;
import com.example.circular.annotation.Lazy;
import com.example.circular.entity.C;
import lombok.SneakyThrows;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * <p>
 *          bean 注入
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class InjectBean {

    /** 被扫描到的类 **/
    private List<Class<?>> registerBeanClasses = new ArrayList<>();
    /** 实例化池，如果Class代表的对象被实例化，那么这里面将会存储实例化后的对象 **/
    private Map<Class<?>, Object> beanMapping = new HashMap<>();
    /** 实例化完毕，注入完毕的对象池 **/
    private List<Object> instancePool = new ArrayList<>();


    @SneakyThrows
    public void inject (String basePackage) {
        Assert.isNotEmpty(basePackage);
        // 包扫描，  com.example.circular.entity， 下面会扫描出三个类
        // A B C ->放入 registerBeanClasses 中
        this.doScanner(basePackage);

        // 循环每一个被扫描到的类，为其实例化，并完成依赖注入
        for (Class<?> beanClazz : registerBeanClasses) {
            // 实例化本身，先从实例化池中获取被实例化的对象
            Object beanInstance = this.beanMapping.get(beanClazz);
            if (null == beanInstance) {
                // 反射创建对象
                beanInstance = beanClazz.newInstance();
                // 实例化完毕后放入容器池
                this.beanMapping.put(beanClazz, beanInstance);
            }

            // 拿到每个类中所有的 包括 private 修饰的成员变量
            Field[] fields = beanClazz.getDeclaredFields();

            for (Field field : fields) {
                // 获取到成员变量的类型
                Class<?> type = field.getType();

                // private 修饰的，允许强势操作
                field.setAccessible(true);
                Object fieldInstance = null;

                // 如果扫描到该成员变量，并且该成员变量没有被实例化
                if (this.registerBeanClasses.contains(type)
                        && !this.beanMapping.containsKey(type)) {
                    // 那么该成员变量将会被实例化
                    fieldInstance = type.newInstance();
                    // 实例化后，放入容器中，保证不会被第二次实例化
                    this.beanMapping.put(type, fieldInstance);
                }
                // 如果对象被实例化了，那么直接取值然后赋值就可以
                else if (this.beanMapping.containsKey(type)){
                    fieldInstance = this.beanMapping.get(type);
                }

                if (!type.isAnnotationPresent(Lazy.class)) {
                    // 为当前对象，赋值上成员变量的实例
                    field.set(beanInstance, fieldInstance);
                }
            }
            // 将所有的实例化的类，放入到实例池中，方便观察
            this.instancePool.add(beanInstance);
        }

        this.lazyInitChoose();
    }

    /**
     * 扫描传入的包下的所有对象，将扫描到的类，放入到 registerBeanClasses 中，等待实例化
     * @param scanPackage    com.example.circular.entity
     */
    @SneakyThrows
    private void doScanner(String scanPackage) {
        String path = scanPackage.replace(".", "/");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        Enumeration<URL> resources = classloader.getResources(path);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            File classPath = new File(url.getFile());
            //当前是一个ClassPath文件夹
            for (File file : Objects.requireNonNull(classPath.listFiles())) {
                if (file.isDirectory()) {
                    doScanner(scanPackage + "." + file.getName());
                } else {
                    if (!file.getName().endsWith(".class")) {
                        continue;
                    }
                    //全类名 = 包名.类名
                    String className = (scanPackage + "." + file.getName().replace(".class", ""));
                    this.registerBeanClasses.add(Class.forName(className));
                }
            }
        }
    }


    private void lazyInitChoose(){
        for (Object o : this.instancePool) {
            if (o instanceof C) {
                C c = (C) o;
                c.useObject();
            }
        }
    }


    public static void main(String[] args) {
        InjectBean injectBean = new InjectBean();
        injectBean.inject("com.example.circular.entity");
    }
}
