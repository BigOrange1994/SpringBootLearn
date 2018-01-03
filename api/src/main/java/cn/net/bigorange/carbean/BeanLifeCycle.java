package cn.net.bigorange.carbean;

import cn.net.bigorange.ApiApplication;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.StringValueResolver;

import java.beans.PropertyEditor;
import java.security.AccessControlContext;

/**
 * Created by think on 2017/12/26.
 */
@ComponentScan("cn.net.bigorange")
@SpringBootApplication
public class BeanLifeCycle {

    private static  void LifeCycleInBeanFactory(ApplicationContext ctx){

        ConfigurableBeanFactory bf = (ConfigurableBeanFactory) ctx.getAutowireCapableBeanFactory();
        Car car1 = (Car) bf.getBean("car");
        car1.introduce();
        car1.setColor("红色");
        car1.introduce();
        Car car2 = (Car) bf.getBean("car");
        System.out.println("car1==car2;" + (car1 == car2));
        // 关闭容器
        bf.destroySingletons();
    }

    public static void main(String[] args) {
        ApplicationContext ctx   = (ApplicationContext)SpringApplication.run(cn.net.bigorange.carbean.BeanLifeCycle.class, args);
        LifeCycleInBeanFactory(ctx);
    }

}
