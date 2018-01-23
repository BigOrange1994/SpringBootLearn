package cn.net.bigorange.helper.springincident;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by bigorange on 2018/1/23.
 */
public class MailSender implements ApplicationContextAware{

    private  ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public void sendMail(String to){
        System.out.println("MailSender模拟发送邮件");
        MailSendEvent mse = new MailSendEvent(this.ctx, to);
        // 向容器中所有的监听器发送事件
        ctx.publishEvent(mse);
    }

}
