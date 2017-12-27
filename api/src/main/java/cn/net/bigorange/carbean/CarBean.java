package cn.net.bigorange.carbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by think on 2017/12/26.
 */

@Configuration
@ComponentScan(basePackages = {"cn.net.bigorange.carbean"})
public class CarBean {

    @Bean(initMethod = "myInitMethod", destroyMethod = "myDestoryMethod", name = "car")
    Car setCarBean(){
        Car car = new Car();
        car.setSpeed(200);
        car.setBrand("红旗CA72");
        return car;
    }

}
