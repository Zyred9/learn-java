package com.example.rpc.v2.process;

import com.example.rpc.v2.annotion.RemoteService;
import com.example.rpc.v2.bean.BeanMethod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Component
public class InitialMediator implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        // 将包含 @RemoteService 注解类的所有方法进行扫描
        if (!bean.getClass().isAnnotationPresent(RemoteService.class)) {
            return bean;
        }

        Method[] methods = bean.getClass().getDeclaredMethods();
        for (Method method : methods) {

            // key = com.example.rpc.IUserService.getUserById
            String mapKey = bean.getClass().getInterfaces()[0].getName().concat(".").concat(method.getName());
            // 组装 bean Method
            BeanMethod beanMethod = new BeanMethod();
            beanMethod.setBean(bean);
            beanMethod.setMethod(method);

            // 放入到中介容器中
            Mediator.mediator.put(mapKey, beanMethod);
        }


        return bean;
    }
}
