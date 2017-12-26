package cn.net.bigorange.carbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * Created by think on 2017/12/26.
 */
@Configuration
public class MyBeanPostProcesser implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("car")){
            Car car = (Car) bean;
            if(car.getColor() ==null){
                System.out.println("BeanPostProcessor.postProcessBeforeInitialization()");
                car.setColor("black");
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("car")){
            Car car = (Car) bean;
            if(car.getSpeed() >= 200){
                System.out.println("BeanPostProcessor.postProcessAfterInitialization()");
                car.setSpeed(200);
            }
        }
        return bean;
    }
}
