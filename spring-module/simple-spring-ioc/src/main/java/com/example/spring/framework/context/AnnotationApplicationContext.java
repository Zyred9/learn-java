package com.example.spring.framework.context;

import com.example.spring.framework.annotation.Autowired;
import com.example.spring.framework.annotation.Controller;
import com.example.spring.framework.annotation.Service;
import com.example.spring.framework.aop.JdkDynamicProxy;
import com.example.spring.framework.aop.config.AopConfig;
import com.example.spring.framework.aop.support.AdvisedSupport;
import com.example.spring.framework.beans.BeanWrapper;
import com.example.spring.framework.beans.config.BeanDefinition;
import com.example.spring.framework.support.BeanDefinitionReader;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * context 模块，包含IOC容器等
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Slf4j
public class AnnotationApplicationContext implements ApplicationContext {

    private String[] configLocations;
    private BeanDefinitionReader reader;
    /** 对Bean的信息进行存储 **/
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    /** IOC单例容器 **/
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();
    /** 二级缓存容器，被初始化后的实例缓存容器 **/
    private Map<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();


    public AnnotationApplicationContext(String... configLocations) {
        this.configLocations = configLocations;

        try {
            /** 读取配置文件加载 **/
            this.reader = new BeanDefinitionReader(this.configLocations);
            /** 获取到所有被扫描到的类，且被封装成BeanDefinition的集合 **/
            List<BeanDefinition> beanDefinitions = this.reader.loadBeanDefinitions();
            /** 将Bean注册放入到IOC容器中 **/
            this.doRegisterBeanDefinition(beanDefinitions);
            /** 完成自动注入 **/
            this.doAutowired();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 将 BeanDefinition 存到 beanDefinitionMap 中
     *
     * @param beanDefinitions
     */
    private void doRegisterBeanDefinition(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition definition : beanDefinitions) {
            if (this.beanDefinitionMap.containsKey(definition.getFactoryName())) {
                throw new RuntimeException("Bean " + definition.getFactoryName() + " is exist !");
            }
            this.beanDefinitionMap.put(definition.getFactoryName(), definition);
            this.beanDefinitionMap.put(definition.getBeanClassName(), definition);
        }
    }

    /**
     * 自动注入
     */
    private void doAutowired() {
        Set<String> keySet = this.beanDefinitionMap.keySet();
        try {
            for (String beanName : keySet) {
                this.getBean(beanName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Bean 的实例化
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        // 获取到当前BeanName 对应的Definition对象
        BeanDefinition definition = this.beanDefinitionMap.get(beanName);
        // 反射获取实例
        Object instance = this.doInstance(beanName, definition);
        // 得到包装类
        BeanWrapper beanWrapper = new BeanWrapper(instance);
        // 将包装类放入到 IOC中
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);
        // 执行核心的依赖注入操作
        populateBean(beanWrapper);
        // 返回实体
        return beanWrapper.getWrapperInstance();
    }


    /**
     * 通过反射实例化对象，并且将实例存入 factoryBeanObjectCache 中进行保存
     *
     * @param beanName
     * @param definition
     * @return
     */
    private Object doInstance(String beanName, BeanDefinition definition) {

        String beanClassName = definition.getBeanClassName();
        Object instance = null;
        try {
            Class<?> clazz = Class.forName(beanClassName);
            instance = clazz.newInstance();

            /***************AOP****************/
            // 初始化配置AOP配置文件
            AdvisedSupport support = instantInitAopConfig();
            if (Objects.nonNull(support)) {

                support.setTargetClass(clazz)
                        .setTarget(instance);
                /**
                 *  判断规则，要不要生成代理类，如果要就覆盖原生对象
                 *  如果不要就不做任何处理，返回原生对象
                 **/
                if (support.pointCutMath()) {
                    instance = new JdkDynamicProxy(support).getProxy();
                    log.info("AspectJ ==> {}", instance.getClass());
                }
            }
            /***************AOP****************/


            this.factoryBeanObjectCache.put(beanName, instance);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private AdvisedSupport instantInitAopConfig() {
        Properties realConfig = this.reader.getConfig();
        if (Objects.isNull(realConfig.getProperty("pointCut"))) {
            return null;
        }
        AopConfig config = new AopConfig()
                .setPointCut(realConfig.getProperty("pointCut"))
                .setAspectAfter(realConfig.getProperty("aspectAfter"))
                .setAspectAfterThrow(realConfig.getProperty("aspectAfterThrow"))
                .setAspectAfterThrowingName(realConfig.getProperty("aspectAfterThrowingName"))
                .setAspectBefore(realConfig.getProperty("aspectBefore"))
                .setAspectClass(realConfig.getProperty("aspectClass"));
        return new AdvisedSupport(config);
    }

    /**
     * Bean 的依赖注入
     * <p>
     * 在这里面需要处理的是循环依赖的问题。
     *
     * @param beanWrapper 包装器
     */
    private void populateBean(BeanWrapper beanWrapper) {
        Object instance = beanWrapper.getWrapperInstance();

        Class<?> wrapperClazz = beanWrapper.getWrapperClazz();

        // 如果没有包含 @Controller 或者 @Service 注解，则不需要注入
        if (!(wrapperClazz.isAnnotationPresent(Controller.class)
                || wrapperClazz.isAnnotationPresent(Service.class))) {
            return;
        }

        // 获取到类中所有的成员变量
        Field[] fields = wrapperClazz.getDeclaredFields();
        for (Field field : fields) {
            // 只针对有注入迹象的对象进行操作
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }

            Autowired autowired = field.getAnnotation(Autowired.class);

            String autowiredBeanName = autowired.value().trim();
            // beanName = Objects.equals("", autowiredBeanName) ? beanName : autowiredBeanName;
            if (Objects.equals("", autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }

            // 强制访问
            field.setAccessible(true);
            if (null == this.factoryBeanInstanceCache.get(autowiredBeanName)) {
                continue;
            }

            try {
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());
            }catch (IllegalAccessException ex){
                ex.printStackTrace();
                continue;
            }
        }
    }


    /**
     * 提供外部调用
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T getBean(Class<T> clazz){
        return (T) this.getBean(clazz.getName());
    }

    @Override
    public Set<String> getAllBean() {
        return this.factoryBeanObjectCache.keySet();
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    @Override
    public Properties getConfig() {
        return this.reader.getConfig();
    }
}
