package cn.net.bigorange.helper.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by bigorange on 2018/1/15.
 */
@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServerStarter.class, args);
    }

}
