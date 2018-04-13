package cn.net.bigorange.clock;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by bigorange on 2018/4/12.
 */
@Slf4j
public class RedisLock {

    // 加锁超时时间，单位：毫秒。加锁期间内完成加锁操作，若未完成会发生并发现象
    private long lockTimeOut;

    private RedisTemplate redisTemplate;

    public RedisLock(RedisTemplate redisTemplate, long lockTimeOut){
        this.lockTimeOut = lockTimeOut;
        this.redisTemplate = redisTemplate;
    }

    private boolean setNX(final String key, final String value){
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                    boolean success = connection.setNX(stringRedisSerializer.serialize(key),
                            stringRedisSerializer.serialize(value));
                    connection.close();
                    return success;
                }
            });
        } catch (Exception e) {
            System.out.println("set NX Redis Error, key :{}" +  key);
           //log.info("set NX Redis Error, key :{}", key);
        }
        return obj != null ? (boolean) obj : false;
    }

    private String get(final String key){
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                    byte[] data = connection.get(stringRedisSerializer.serialize(key));
                    connection.close();
                    if(null == data){
                        return null;
                    }
                    return stringRedisSerializer.deserialize(data);
                }
            });
        } catch (Exception e) {
            System.out.println("get Redis Error, key :{}" + key);
            //log.info("get Redis Error, key :{}", key);
        }
        return  obj != null ? obj.toString() : null;
    }

    private String getSet(final String key, final String value){
        Object obj = null;
        try{
            obj  = redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer stringRedisSerializer= new StringRedisSerializer();
                    byte[] data = connection.getSet(stringRedisSerializer.serialize(key), stringRedisSerializer.serialize(value));
                    connection.close();
                    return stringRedisSerializer.deserialize(data);
                }
            });
        }
        catch (Exception e){
            System.out.println("getSet Redis Error, key :{}" +  key);
            //log.info("getSet Redis Error, key :{}", key);
        }
        return  obj != null ? obj.toString() : null;
    }

    private boolean del(final String key){
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                    Long del = connection.del(stringRedisSerializer.serialize(key));
                    connection.close();
                    return del;
                }
            });
            return true;
        } catch (Exception e) {
            //log.info("del Redis Error, key :{}", key);
            System.out.println("del Redis Error, key :{}"+  key);
        }
        return false;
    }

    public boolean expire(final String key, final Long expireTime){
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                    boolean result = connection.expire(stringRedisSerializer.serialize(key), expireTime );
                    connection.close();
                    return result;
                }
            });
            return (Boolean)obj;
        } catch (Exception e) {
            System.out.println("expire Redis Error, key :{}"+  key);
            //log.info("expire Redis Error, key :{}", key);
        }
        return false;
    }

    /**
     * 获取当前的redis时间
     * @return
     */
    public long getCurrentRedisTime(){
        Object obj = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.time();
            }
        });
        return obj == null ? -1 : (Long)obj;
    }

    /**
     * 加锁
     * @param key
     * @param threadName
     * @return
     */
    public synchronized long lock(final String key, final String threadName){
        //log.debug(threadName + "开始执行加锁");
        System.out.println(threadName + "开始执行加锁");
        long lock_time_out = getCurrentRedisTime() + lockTimeOut;
        if(setNX(key, String.valueOf(lock_time_out))){
            System.out.println("加锁成功！+1");
            //log.debug("加锁成功！+1");
            expire(key, lockTimeOut);
            return lock_time_out;
        } else {
            Object result = get(key);
            Long current_lock_timeout = result == null ? null : Long.parseLong(result.toString());
            if(current_lock_timeout != null && current_lock_timeout < getCurrentRedisTime()){
                Long old_lock_timeout = Long.valueOf(getSet(key, String.valueOf(lock_time_out)));
                if(null != old_lock_timeout && current_lock_timeout == old_lock_timeout){
                    //log.debug("加锁成功！+2");
                    System.out.println("加锁成功！+2");
                    expire(key, lockTimeOut);
                    return lock_time_out;
                }
            }
        }
        return -1;
    }

    /**
     * 解锁
     * @param key
     * @param value
     * @param threadName
     */
    public synchronized void unlock(String key, long value, String threadName) {
       // log.debug(threadName + "开始执行**解锁**");
        System.out.println(threadName + "开始执行**解锁**");
        String result = get(key);
        System.out.println("获取到的lockValue为："+ value + ", 当前的线程为：" + threadName);
        //log.debug("获取到的lockValue为：[{}], 当前的线程为：[{}]", value, threadName);
        Long current_lock_timeout = result == null ? null : Long.valueOf(result);
        if(current_lock_timeout != null && current_lock_timeout == value){
            del(key);
            System.out.println(threadName + "解锁成功！");
            //log.debug(threadName + "解锁成功！");
        }
    }

}