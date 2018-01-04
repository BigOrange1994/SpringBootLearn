package cn.net.bigorange.helper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Created by bigorange on 2018/1/4.
 */
public class TestRedisCluster {

    public static void main(String[] args) {
        ApplicationContext context = null;
        try{
            context = new ClassPathXmlApplicationContext("classpath:config/redis/spring-redis-cluster.xml");
        }catch(Exception e){
            e.printStackTrace();
        }
        RedisTemplate<String, Object> template = (RedisTemplate<String, Object>) context.getBean("redisTemplateCluster");
        try {
            ValueOperations<String, Object> operations =
                    template.opsForValue();
            operations.set("testCluster", 1);
            System.out.println(template.hasKey("testCluster") + "=========");
            System.out.println(template.hasKey("testCluster111") + "=========");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
