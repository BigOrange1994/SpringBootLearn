package cn.net.bigorange.helper.advisor;

import cn.net.bigorange.advice.Seller;
import cn.net.bigorange.advice.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bigorange on 2018/3/8.
 */
public class TestStaticMethodMatcherPointcutAdvisor {

    public static void main(String[] args) {
        String xmlClassPath = "config/advisor/spring-advisor.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(xmlClassPath);
        Waiter waiter = (Waiter) ctx.getBean("waiterProxy");
        Seller seller = (Seller) ctx.getBean("sellerProxy");
        waiter.greetingTo("Peter");
        waiter.serveTo("Peter");
        waiter.greetingTo("John");
        waiter.serveTo("John");
        //seller.greetingTo("John");
    }

}
