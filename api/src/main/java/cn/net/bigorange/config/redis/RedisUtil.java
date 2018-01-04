package cn.net.bigorange.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Created by bigorange on 2018/1/2.
 */
public class RedisUtil {

    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public boolean expire(String key, long time){
        try{
            if(time > 0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            return  false;
        }
    }

    public long getExpireTime(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key,Object value) {
        try {
            ValueOperations<String, Object> operations =
                    redisTemplate.opsForValue();
            operations.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public String ping() {
       return
            redisTemplate.getConnectionFactory().getConnection().ping();
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }


}


