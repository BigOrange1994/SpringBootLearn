package cn.net.bigorange.helper.springincident;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by bigorange on 2018/1/23.
 */
public class MailSendListener implements ApplicationListener<MailSendEvent>{

    @Override
    public void onApplicationEvent(MailSendEvent event) {
        MailSendEvent mse = event;
        System.out.println("MailSendEvent向" + mse.getTo() + "发完一封邮件");
    }

}
