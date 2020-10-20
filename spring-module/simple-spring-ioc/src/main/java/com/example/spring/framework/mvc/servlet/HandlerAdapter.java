package com.example.spring.framework.mvc.servlet;

import com.example.spring.framework.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *          初始化参数适配器
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class HandlerAdapter {

    public ModelAndView handler(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) throws Exception{

        //保存形参列表
        //将参数名称和参数的位置，这种关系保存起来
        Map<String,Integer> paramIndexMapping = new HashMap<String, Integer>();

        //通过运行时的状态去拿到你
        Annotation[] [] pa = handler.getMethod().getParameterAnnotations();
        for (int i = 0; i < pa.length ; i ++) {
            for(Annotation a : pa[i]){
                if(a instanceof RequestParam){
                    String paramName = ((RequestParam) a).value();
                    if(!"".equals(paramName.trim())){
                        paramIndexMapping.put(paramName,i);
                    }
                }
            }
        }

        //初始化一下
        Class<?> [] paramTypes = handler.getMethod().getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> parameterType = paramTypes[i];
            if(parameterType == HttpServletRequest.class || parameterType == HttpServletResponse.class){
                paramIndexMapping.put(parameterType.getName(),i);
            }
        }


        //去拼接实参列表
        //http://localhost/web/query?name=Tom&Cat
        Map<String,String[]> params = req.getParameterMap();

        Object [] paramValues = new Object[paramTypes.length];

        for (Map.Entry<String,String[]> param : params.entrySet()) {
            String value = Arrays.toString(params.get(param.getKey()))
                    .replaceAll("\\[|\\]","")
                    .replaceAll("\\s+",",");

            if(!paramIndexMapping.containsKey(param.getKey())){continue;}

            int index = paramIndexMapping.get(param.getKey());

            //允许自定义的类型转换器Converter
            paramValues[index] = castStringValue(value,paramTypes[index]);
        }

        if(paramIndexMapping.containsKey(HttpServletRequest.class.getName())){
            int index = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[index] = req;
        }

        if(paramIndexMapping.containsKey(HttpServletResponse.class.getName())){
            int index = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[index] = resp;
        }

        Method method = handler.getMethod();

        if (method == null){
            return null;
        }

        Object result = method.invoke(handler.getController(),paramValues);
        if(result == null || result instanceof Void){return null;}

        boolean isModelAndView = handler.getMethod().getReturnType() == ModelAndView.class;
        if(isModelAndView){
            return (ModelAndView)result;
        }
        return null;
    }

    private Object castStringValue(String value, Class<?> paramType) {
        if(String.class == paramType){
            return value;
        }else if(Integer.class == paramType){
            return Integer.valueOf(value);
        }else if(Double.class == paramType){
            return Double.valueOf(value);
        }else {
            if(value != null){
                return value;
            }
            return null;
        }
    }
}
