package cn.net.bigorange.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by bigorange on 2018/1/25.
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        String clientName = (String)args[0];
        System.out.println("How are you " + clientName + "." + target.getClass().getName() + ", " + method.getName());
    }

}
