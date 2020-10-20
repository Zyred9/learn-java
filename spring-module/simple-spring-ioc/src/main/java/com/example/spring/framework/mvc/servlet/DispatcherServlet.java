package com.example.spring.framework.mvc.servlet;

import cn.hutool.core.collection.CollectionUtil;
import com.example.spring.framework.annotation.Controller;
import com.example.spring.framework.annotation.RequestMapping;
import com.example.spring.framework.context.AnnotationApplicationContext;
import com.example.spring.framework.context.ApplicationContext;
import com.example.test.ControllerTest;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * <p>
 * DispatcherServlet, 重写 doGet doPost
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Slf4j
public class DispatcherServlet extends HttpServlet {

    private final String INIT_PARAM = "contextConfigLocation";
    private final String TEMPLATE = "templateRoot";
    /**
     * spring 核心组件
     **/
    private ApplicationContext context;
    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    /**
     * 方法和参数的映射关系
     **/
    private Map<HandlerMapping, HandlerAdapter> handlerAdapters = new ConcurrentHashMap<>();

    private List<ViewResolver> viewResolvers = new ArrayList<>();

    @Override
    public void init(ServletConfig config) {
        // 初始化spring核心组件 ApplicationContext
        context = new AnnotationApplicationContext(config.getInitParameter(this.INIT_PARAM));
        // 初始化springMvc九大组件
        initStrategies(context);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                processDispatchResult(resp, new ModelAndView("500"));
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(e.getStackTrace()));
            }
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HandlerMapping handler = getHandlerMapping(req);
        if (null == handler) {
            this.processDispatchResult(resp, new ModelAndView("404"));
        }

        // 通过Handler获取到参数关系
        HandlerAdapter adapter = this.getHandlerAdapter(handler);

        ModelAndView mv = adapter.handler(req, resp, handler);

        processDispatchResult(resp, mv);
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        return this.handlerAdapters.get(handler);
    }

    private void initStrategies(ApplicationContext context) {
        //多文件上传的组件
        //initMultipartResolver(context);
        //初始化本地语言环境
        //initLocaleResolver(context);
        //初始化模板处理器
        //initThemeResolver(context);
        //初始化处理器映射器
        initHandlerMappings(context);
        //初始化参数适配器
        initHandlerAdapters();
        //初始化异常拦截器
        //initHandlerExceptionResolvers(context);
        //初始化视图预处理器
        //initRequestToViewNameTranslator(context);
        //初始化视图转换器
        initViewResolvers(context);
        //FlashMap管理器
        // initFlashMapManager(context);
    }

    /**
     * 初始化处理器映射器
     * 取拥有 @Controller 注解的类，并且取出所有的 @RequestMapping 的value属性，和对应的Method关联
     *
     * @param context
     */
    private void initHandlerMappings(ApplicationContext context) {
        if (context.getBeanDefinitionCount() == 0) {
            return;
        }

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = this.context.getBean(name);

            // 这里只需要处理 @Controller注解的类
            Class<?> beanClazz = bean.getClass();
            if (!beanClazz.isAnnotationPresent(Controller.class)) {
                continue;
            }

            String url = "";
            // 取类上的 @RequestMapping 注解
            if (beanClazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping reqMapping = beanClazz.getAnnotation(RequestMapping.class);
                url += reqMapping.value().trim();
            }

            // 获取所有的方法
            Method[] methods = beanClazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                // //user//name/*
                String methodUrl = "/" + url + "/" + method.getAnnotation(RequestMapping.class).value().trim();
                url = methodUrl
                        .replaceAll("/+", "/")
                        .replaceAll("\\*", ".*");
                Pattern pattern = Pattern.compile(url);

                // TODO  需要判断重复的Mapping
                log.info("Mapping {}", (bean.getClass().getName() +"."+ method.getName()+"()" + "-->" + url) );
                this.handlerMappings.add(new HandlerMapping(pattern, bean, method));
            }
        }
    }

    /**
     * 初始化参数映射器
     */
    private void initHandlerAdapters() {

        if (CollectionUtil.isEmpty(this.handlerMappings)) {
            throw new RuntimeException("No such any mappings case :" + handlerMappings);
        }

        for (HandlerMapping mapping : this.handlerMappings) {
            this.handlerAdapters.put(mapping, new HandlerAdapter());
        }
    }

    /**
     * 初始化试图解析器
     *
     * @param context
     */
    private void initViewResolvers(ApplicationContext context) {
        String template = context.getConfig().getProperty(this.TEMPLATE);
        String root = this.getClass().getClassLoader().getResource(template).getFile();

        File rootFile = new File(root);
        for (File file : Objects.requireNonNull(rootFile.listFiles())) {
            log.info("File {}", file.getAbsoluteFile());
            viewResolvers.add(new ViewResolver(file, rootFile));
        }
    }

    private HandlerMapping getHandlerMapping(HttpServletRequest req) {
        if (CollectionUtil.isEmpty(this.handlerMappings)) {
            return null;
        }

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        uri = uri.replaceAll("/+", "/")
                .replaceAll(contextPath, "")
                .trim();
        if (Objects.equals("/favicon.ico", uri)) {
            return new HandlerMapping(Pattern.compile("/favicon.ico"), new ControllerTest(), null);
        }

        for (HandlerMapping mapping : this.handlerMappings) {
            if (!mapping.getPattern().matcher(uri).matches()) {
                continue;
            }
            return mapping;
        }
        return null;
    }


    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) throws IOException {
        if (null == mv) {
            return;
        }
        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (ViewResolver viewResolver : this.viewResolvers) {
            View view = viewResolver.resolveViewName(mv.getViewName());
            //直接往浏览器输出
            view.render(mv.getModel(), resp);
            return;
        }
    }
}
