package cn.net.bigorange.helper.advice;

import cn.net.bigorange.advice.GreetingBeforeAdvice;
import cn.net.bigorange.advice.NativeWaiter;
import cn.net.bigorange.advice.Waiter;
import cn.net.bigorange.monitor.Monitorable;
import cn.net.bigorange.service.jdkproxy.impl.ForumServiceImpl;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bigorange on 2018/1/25.
 */
public class TestAdvice {

    public static void main(String[] args) {
        //beforeAdvice();
        interceptorAdvice();
    }

    public static void beforeAdvice(){
        Waiter waiter = new NativeWaiter();
        BeforeAdvice advice = new GreetingBeforeAdvice();

        // Spring提供的代理工场
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(waiter);
        pf.addAdvice(advice);

        // 生成代理实例
        Waiter proxyWaiter = (Waiter)pf.getProxy();
        proxyWaiter.greetingTo("Tom");
        proxyWaiter.serveTo("John");
    }

    public static void interceptorAdvice(){
        String path = "config/advice/spring-advice.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(path);
        ForumServiceImpl impl = (ForumServiceImpl) ctx.getBean("forunService");
        impl.removeTopic(1);
        impl.removeForum(2);
        Monitorable monitorable = (Monitorable) impl;
        monitorable.setMonitorActice(true);
        impl.removeTopic(3);
        impl.removeForum(4);
    }

}
