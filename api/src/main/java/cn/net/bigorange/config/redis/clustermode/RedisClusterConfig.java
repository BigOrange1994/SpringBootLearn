package cn.net.bigorange.config.redis.clustermode;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by bigorange on 2018/1/4.
 */
@Configuration
@ImportResource(locations = {"classpath:config/redis/spring-redis-cluster.xml"})
public class RedisClusterConfig extends CachingConfigurerSupport {

}
