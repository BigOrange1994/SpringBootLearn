package cn.net.bigorange.helper.springincident;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bigorange on 2018/1/23.
 */
public class TestSpringIncident {

    public static void main(String[] args) {
        String[] config = {"config/springincident/spring-incident.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        MailSender sender = (MailSender) ctx.getBean("eventSender");
        sender.sendMail("沈阳");
    }

}
