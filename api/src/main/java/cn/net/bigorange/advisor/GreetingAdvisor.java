package cn.net.bigorange.advisor;

import cn.net.bigorange.advice.Waiter;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * Created by bigorange on 2018/3/8.
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor{

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        boolean result = "greetingTo".equals(method.getName());
        return result;
    }

    public ClassFilter getClassFilter(){
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return Waiter.class.isAssignableFrom(clazz);
            }
        };
    }

}
