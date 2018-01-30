package cn.net.bigorange.interceptor;

import cn.net.bigorange.monitor.Monitorable;
import cn.net.bigorange.monitor.PerformanceMonitor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by bigorange on 2018/1/30.
 */
public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitorable{

    private ThreadLocal<Boolean> monitorStatusMap = new ThreadLocal<Boolean>();

    @Override
    public void setMonitorActice(boolean active) {
        monitorStatusMap.set(active);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object obj = null;
        if(monitorStatusMap.get() != null && monitorStatusMap.get()){
            PerformanceMonitor.begin(mi.getClass().getName() + "." + mi.getMethod().getName());
            obj = super.invoke(mi);
            PerformanceMonitor.end();
        }else{
            obj = super.invoke(mi);
        }
        return obj;
    }

}
