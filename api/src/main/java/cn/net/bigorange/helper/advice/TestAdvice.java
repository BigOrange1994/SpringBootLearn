package cn.net.bigorange.helper.advice;

import cn.net.bigorange.advice.GreetingBeforeAdvice;
import cn.net.bigorange.advice.NativeWaiter;
import cn.net.bigorange.advice.Waiter;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by bigorange on 2018/1/25.
 */
public class TestAdvice {

    public static void main(String[] args) {
        beforeAdvice();
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

}
