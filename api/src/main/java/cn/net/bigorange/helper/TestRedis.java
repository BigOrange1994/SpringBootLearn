package cn.net.bigorange.helper;

import cn.net.bigorange.config.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * Created by bigorange on 2018/1/2.
 */
public class TestRedis {

    public static void main(String[] args) {
        ApplicationContext context = null;
        try{
             context = new ClassPathXmlApplicationContext("classpath:config/redis/spring-redis.xml");
        }catch(Exception e){

        }
        RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
        System.out.println(redisUtil.ping()+ "****************============");
        System.out.println(redisUtil.get("test1")+ "============");
        System.out.println(redisUtil.set("test1", "22")+ "============");
    }

}
