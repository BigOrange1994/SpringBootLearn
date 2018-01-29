package cn.net.bigorange.handler;

import cn.net.bigorange.monitor.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by bigorange on 2018/1/24.
 */
public class PerformanceHandler implements InvocationHandler{

    private Object target;

    public PerformanceHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());
        Object obj = method.invoke(target, args);
        PerformanceMonitor.end();
        return obj;
    }

}
