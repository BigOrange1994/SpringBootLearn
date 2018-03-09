package cn.net.bigorange.pointcut;

import cn.net.bigorange.advice.NativeWaiter;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigorange on 2018/3/8.
 */
public class CusDynamicMethodMacherPointcut extends DynamicMethodMatcherPointcut{

    private static List<String> specialClientList = new ArrayList<String>();

    static {
        specialClientList.add("John");
        specialClientList.add("Tom");
    }

    // 对类进行静态切点检查
    public ClassFilter getClassFilter(){
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                System.out.println("调用getClassFilter()方法，对" + clazz.getName() + "进行静态检查");
                return NativeWaiter.class.isAssignableFrom(clazz);
            }
        };
    }

    // 对方法进行静态切点检查
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("调用matches()方法，对" + targetClass.getName() + "." + method.getName() + "进行静态检查");
        return "greetingTo".equals(method.getName());
    }

    // 对方法进行动态切点检查
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("调用matches()方法，对" + targetClass.getName() + "." + method.getName() + "进行动态检查");
        String clientName = (String)args[0];
        return specialClientList.contains(clientName);
    }

}
