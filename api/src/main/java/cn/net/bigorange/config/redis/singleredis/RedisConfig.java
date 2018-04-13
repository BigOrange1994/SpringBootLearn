package cn.net.bigorange.config.redis.singleredis;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by lingzihua on 17/9/23.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RedisConfig.class);

    @Value("${data.redis.defaultExpiration}")
    private Long defaultExpiration;
    @Value("${redis.master.host}")
    private String masterHost;
    @Value("${redis.master.port}")
    private int masterPort;
    @Value("${redis.master.name}")
    private String masterName;
    @Value("${redis.sentinel1.host}")
    private String sentinel1Host;
    @Value("${redis.sentinel1.port}")
    private int sentinel1port;
    @Value("${redis.sentinel2.host}")
    private String sentinel2Host;
    @Value("${redis.sentinel2.port}")
    private int sentinel2port;
    @Value("${redis.sentinel3.host}")
    private String sentinel3Host;
    @Value("${redis.sentinel3.port}")
    private int sentinel3port;

    private RedisConnectionFactory generateDevConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(masterHost);
        factory.setPort(masterPort);
        factory.setUsePool(true);
        factory.setConvertPipelineAndTxResults(true);
        JedisPoolConfig poolConfig = generatePoolConfig();
        factory.setPoolConfig(poolConfig);
        factory.afterPropertiesSet();
        return factory;
    }

    private RedisConnectionFactory generateReleaseConnectionFactory() {
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        RedisNode master = new RedisNode(masterHost, masterPort);
        master.setName(masterName);
        Set<RedisNode> sentinels = new HashSet<>();
        RedisNode sentinel1 = new RedisNode(sentinel1Host, sentinel1port);
        RedisNode sentinel2 = new RedisNode(sentinel2Host, sentinel2port);
        RedisNode sentinel3 = new RedisNode(sentinel3Host, sentinel3port);
        sentinels.add(sentinel1);
        sentinels.add(sentinel2);
        sentinels.add(sentinel3);
        sentinelConfiguration.setSentinels(sentinels);
        sentinelConfiguration.setMaster(master);

        JedisPoolConfig poolConfig = generatePoolConfig();
        JedisConnectionFactory factory = new JedisConnectionFactory(sentinelConfiguration, poolConfig);
        factory.setHostName(masterHost);
        factory.setPort(masterPort);
        factory.setTimeout(10000);
        factory.setUsePool(true);
        factory.setConvertPipelineAndTxResults(true);
        factory.afterPropertiesSet();
        return factory;
    }


    private JedisPoolConfig generatePoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(20);
        poolConfig.setMaxTotal(300);
        poolConfig.setMaxWaitMillis(5000);
        poolConfig.setTestOnBorrow(true);
        return poolConfig;
    }

    @Bean(name = "redisConnectionFactory")
    RedisConnectionFactory factory() {
        LOGGER.info("初始化redis配置开始");
        if (StringUtils.isEmpty(masterName)) {
            return generateDevConnectionFactory();
        } else {
            return generateReleaseConnectionFactory();
        }
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory factory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        GenericJackson2JsonRedisSerializer genericFastJson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        template.setEnableTransactionSupport(false);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(genericFastJson2JsonRedisSerializer);
        template.setDefaultSerializer(jdkSerializationRedisSerializer);
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean(name = "stringRedisTemplate")
    public RedisTemplate<String, String> stringRedisTemplate(
            RedisConnectionFactory factory) {
        final RedisTemplate<String, String> template = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setEnableTransactionSupport(false);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setDefaultSerializer(stringRedisSerializer);
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean(name = "serializableRedisTemplate")
    public RedisTemplate<Serializable, Serializable> serializableRedisTemplate(
            RedisConnectionFactory factory) {
        final RedisTemplate<Serializable, Serializable> template = new RedisTemplate<Serializable, Serializable>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        template.setEnableTransactionSupport(false);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setDefaultSerializer(jdkSerializationRedisSerializer);
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean(name = "cacheManager")
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(defaultExpiration);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

   /* @Bean(name = "jedis")
    public Jedis getRedisInstance(@Qualifier("redisConnectionFactory")JedisConnectionFactory jedisConnectionFactory) {
        Jedis jedis = (Jedis) jedisConnectionFactory.getConnection()
                .getNativeConnection();
        return jedis;
    }*/

}