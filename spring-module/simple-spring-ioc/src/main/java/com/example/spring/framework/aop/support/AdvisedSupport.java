package com.example.spring.framework.aop.support;

import com.example.spring.framework.aop.aspect.Advice;
import com.example.spring.framework.aop.config.AopConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 解析AOP配置类
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Slf4j
public class AdvisedSupport {

    /** AOP配置类 **/
    private AopConfig config;
    /** 被代理的对象 **/
    private Object target;
    /** 被代理的对象的 Class **/
    private Class targetClass;
    /** 切面的正则表达式 **/
    private Pattern pointCutClassPattern;
    /** 方法的缓存 **/
    private Map<Method, Map<String, Advice>> methodCache;

    public AdvisedSupport(AopConfig config) {
        this.config = config;
    }

    public AdvisedSupport setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
        this.parseConfiguration();
        return this;
    }

    public AdvisedSupport setTarget(Object target) {
        this.target = target;
        return this;
    }

    public Object getTarget() {
        return target;
    }

    /**
     * 解析配置文件
     */
    @SneakyThrows
    public void parseConfiguration() {
        String pointCut = parsePointCut(this.config.getPointCut());
        methodCache = new HashMap<>();

        Pattern pointCutPattern = Pattern.compile(pointCut);
        Class<?> aspectClazz = Class.forName(this.config.getAspectClass());


        Map<String, Method> aspectMethods = new HashMap<>();
        for (Method method : aspectClazz.getMethods()) {
            aspectMethods.put(method.getName(), method);
        }

        for (Method targetMethod : this.targetClass.getMethods()) {
            String targetMethodString = targetMethod.toString();
            // public final void java.lang.Object.wait() throws java.lang.InterruptedException
            if (targetMethodString.contains("throws")) {
                // public final void java.lang.Object.wait()
                targetMethodString = targetMethodString.substring(0, targetMethodString.lastIndexOf("throws")).trim();
            }

            // 如果匹配上了拦截点，那么设置全部配置的拦截点
            Matcher matcher = pointCutPattern.matcher(targetMethodString);
            if (matcher.matches()) {
                Map<String, Advice> advices = new HashMap<>();

                // 生成 Advice 对象映射关系
                this.generatorAdvice(advices, aspectClazz, aspectMethods);
                this.methodCache.put(targetMethod, advices);

                log.info("AspectJ {}", this.targetClass.getName() +"."+ targetMethod.getName());
            }
        }
    }

    /**
     * 根据一个目标代理类的方法，获得其对应的通知
     *
     * @param method 方法
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public Map<String, Advice> getAdvices(Method method) {
        //享元设计模式的应用
        Map<String, Advice> cache = methodCache.get(method);
        if (Objects.isNull(cache)) {

            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());

            cache = methodCache.get(m);
            this.methodCache.put(m, cache);
        }
        return cache;
    }

    /**
     * 给ApplicationContext首先IoC中的对象初始化时调用，决定要不要生成代理类的逻辑
     *
     * @return
     */
    public boolean pointCutMath() {
        boolean matches = pointCutClassPattern.matcher(this.targetClass.toString()).matches();
        if (matches) {
            System.out.println(this.targetClass.toString());
        }
        return matches;
    }

    /**
     * 把SpEL变成Java能够识别的正则表达式
     *
     * @param pointCut
     * @return
     */
    private String parsePointCut(String pointCut) {
        // 将 SpEL 转换成正则表达式
        pointCut = pointCut
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 2);
        // 将每个类AOP类设置上正则表达式
        String substring =  "class " + pointCutForClassRegex
                .substring(pointCutForClassRegex.lastIndexOf(" ") + 1);

        log.info("Regex : {}", substring);

        pointCutClassPattern = Pattern.compile(substring);
        return pointCut;
    }

    /**
     * 生成代理对象信息
     *
     * @param advices
     * @param aspectClazz
     * @param aspectMethod
     */
    @SneakyThrows
    private void generatorAdvice(Map<String, Advice> advices, Class<?> aspectClazz, Map<String, Method> aspectMethod) {
        Object instance = aspectClazz.newInstance();

        // 设置前置处理方法
        if (!(Objects.isNull(config.getAspectBefore()) ||
                Objects.equals("", config.getAspectBefore()))) {

            Method method = aspectMethod.get(config.getAspectBefore());
            Advice advice = new Advice(instance, method);

            advices.put("before", advice);
        }

        // 设置后置处理方法
        if (!(Objects.isNull(config.getAspectAfter()) ||
                Objects.equals("", config.getAspectAfter()))) {

            Method method = aspectMethod.get(config.getAspectAfter());
            Advice advice = new Advice(instance, method);

            advices.put("after", advice);
        }

        // 设置后置异常处理方法
        if (!(Objects.isNull(config.getAspectAfterThrow()) ||
                Objects.equals("", config.getAspectAfterThrow()))) {

            Method method = aspectMethod.get(config.getAspectAfterThrow());
            Advice advice = new Advice(instance, method);
            advice.setThrowName(config.getAspectAfterThrowingName());

            advices.put("afterThrow", advice);
        }
    }

    public Class getTargetClass() {
        return targetClass;
    }
}
