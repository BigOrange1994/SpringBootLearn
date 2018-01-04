package cn.net.bigorange.config.redis.sentinelmode;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
 * Created by bigorange on 2018/1/2.
 */
@Configuration
@ImportResource(locations = {"classpath:config/redis/spring-redis.xml"})
public class RedisConfig extends CachingConfigurerSupport {

}
