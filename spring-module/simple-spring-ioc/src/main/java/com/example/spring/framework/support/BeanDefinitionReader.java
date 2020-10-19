package com.example.spring.framework.support;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import com.example.spring.framework.beans.config.BeanDefinition;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class BeanDefinitionReader {

    /** 存储加载Bean的className **/
    private List<String> registerBeanClasses = new ArrayList<>();

    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

    @SneakyThrows
    public BeanDefinitionReader(String... locations) {
//        String sourceName = locations[0].replaceAll("classPath:", "");
        @Cleanup InputStream stream = new ClassPathResource(locations[0]).getStream();
        this.config.load(stream);

        this.doScanner(config.getProperty(this.SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        String path = scanPackage.replace(".","/");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        try {
            Enumeration<URL> resources = classloader.getResources(path);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                File classPath = new File(url.getFile());
                //当成是一个ClassPath文件夹
                for (File file : classPath.listFiles()) {
                    if(file.isDirectory()){
                        doScanner(scanPackage + "." + file.getName());
                    }else {
                        if(!file.getName().endsWith(".class")){continue;}
                        //全类名 = 包名.类名
                        String className = (scanPackage + "." + file.getName().replace(".class", ""));
                        //Class.forName(className);
                        this.registerBeanClasses.add(className);
                    }
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private Properties getConfig(){
        return this.config;
    }


    /**
     * 将所有被扫描到的类，转换成 BeanDefinition 类，方便IOC操作
     * @return
     */
    public List<BeanDefinition> loadBeanDefinitions(){
        List<BeanDefinition> beanDefinitions = new ArrayList<>();

        try {

            for (String clazzName : this.registerBeanClasses) {
                Class<?> clazz = Class.forName(clazzName);
                if (clazz.isInterface()){
                    continue;
                }
                // 这里需要对类名进行首字母小写转换
                String simpleName = this.caseConversion(clazz.getSimpleName());

                BeanDefinition beanDefinition = new BeanDefinition()
                        .setBeanClassName(clazzName)
                        .setFactoryName(simpleName);

                beanDefinitions.add(beanDefinition);
            }

        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

        return beanDefinitions;
    }

    /**
     * 首字母转小写
     * @param clazzName  类名
     * @return
     */
    private String caseConversion(String clazzName) {
        if (clazzName == null || clazzName == ""){
            throw new RuntimeException("Register bean can not be null case :" + clazzName);
        }
        char[] chars = clazzName.toCharArray();
        // ASCII码
        chars[0] += 32;
        return String.valueOf(chars);
    }



}
