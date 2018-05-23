package cn.net.bigorange.clock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.export.MetricExportProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by bigorange on 2018/4/13.
 */

@RestController
public class TestRedisLock {

    // 系统中的共享资源
    public int source = 500;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(path = "/testRedisLock")
    public void test() throws InterruptedException {
        RedisLock lock = new RedisLock(redisTemplate, 5);
        for (int i = 0; i <= 50; i++) {
            CusThread thread = new CusThread(lock);
            thread.start();

        }
    }

    class  CusThread extends Thread {

        private RedisLock redisLock;

        CusThread(RedisLock redisLock){
            this.redisLock = redisLock;
        }

        @Override
        public void run() {

            //System.out.println(Thread.currentThread().getName()+new Date());
            long lock_time_out = redisLock.lock("resources", Thread.currentThread().getName());
            //System.out.println(Thread.currentThread().getName()+new Date());
            if(lock_time_out != -1){
                System.out.println(source--);
                redisLock.unlock("resources", lock_time_out, Thread.currentThread().getName());
                //System.out.println(Thread.currentThread().getName()+new Date());
            }
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            //System.out.println(Thread.currentThread().getName()+new Date());

            //System.out.println(Thread.currentThread().getName()+new Date());
        }
    }

}
