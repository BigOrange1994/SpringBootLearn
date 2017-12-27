package cn.net.bigorange.carbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by think on 2017/12/26.
 */

// 管理Bean的生命周期
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean{

    // 品牌
    private String brand;
    // 颜色
    private String color;
    // 速度
    private int speed;

    private BeanFactory beanFactory;
    // bean名称
    private String beanName;

    public Car(){
        System.out.println("调用Car构造函数");
    }

    public void setBrand(String brand){
        System.out.println("调用setBrand设置属性");
        this.brand = brand;
    }

    public void introduce(){
        System.out.println("brand=" + brand + "; color=" + color + "; speed=" + speed);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用DisposableBean.destroy()");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("调用BeanNameAware.setBeanName()");
        this.beanName = s;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用InitializingBean.afterPropertiesSet()");
    }

    // 通过<bean> 的init-method属性指定初始化方法
    public void myInitMethod(){
        System.out.println("调用init-method的myInitMethod(),将Speed设置为240");
        this.speed = 240;
    }

    // 通过<bean> 的destory-method属性指定销毁方法
    public void myDestoryMethod(){
        System.out.println("调用init-method的myDestoryMethod()");
    }

    public String getColor(){
        return  this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return this.speed;
    }

    public String getBrand(){
        return  this.brand;
    }

}
