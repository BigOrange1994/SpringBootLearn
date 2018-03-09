package cn.net.bigorange.helper.advisor;

import cn.net.bigorange.advice.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bigorange on 2018/3/9.
 */
public class TestAutoProxy {

    public static void main(String[] args) {
        String xmlClassPath = "config/advisor/spring-advisor.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(xmlClassPath);
        Waiter waiter = (Waiter) ctx.getBean("waiterTarget");
        waiter.serveTo("John");
        waiter.greetingTo("Peter");
    }

}
