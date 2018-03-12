package cn.net.bigorange.helper.aspectj.annocation;

import cn.net.bigorange.advice.NativeWaiter;
import cn.net.bigorange.advice.Waiter;
import cn.net.bigorange.aspectj.annocation.NaughtyWaiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bigorange on 2018/3/12.
 */
public class annocationTest {

    public static void main(String[] args) {
        String configPath  = "config/aspect/spring-aspectj.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter nativeWaiter = (Waiter)ctx.getBean("nativeWaiter");
        Waiter naughtWaiter = (Waiter)ctx.getBean("naughtWaiter");
        nativeWaiter.greetingTo("John");
        naughtWaiter.greetingTo("Peter");
    }

}
