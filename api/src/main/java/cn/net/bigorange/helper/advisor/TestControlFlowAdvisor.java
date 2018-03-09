package cn.net.bigorange.helper.advisor;

import cn.net.bigorange.advice.NativeWaiterDelegate;
import cn.net.bigorange.advice.Seller;
import cn.net.bigorange.advice.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bigorange on 2018/3/8.
 */
public class TestControlFlowAdvisor {

    public static void main(String[] args) {
        String xmlClassPath = "config/advisor/spring-advisor.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(xmlClassPath);
        Waiter waiter = (Waiter) ctx.getBean("waiterProxy");
        NativeWaiterDelegate nativeWaiterDelegate = new NativeWaiterDelegate();
        nativeWaiterDelegate.setWaiter(waiter);
        waiter.serveTo("John");
        waiter.greetingTo("Peter");
        nativeWaiterDelegate.service("Tom");
    }

}
