package cn.net.bigorange.helper.springcloudconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bigorange on 2018/1/15.
 */
@SpringBootApplication
public class SpringCloudConfigClientStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClientStarter.class, args);
    }

}

@RefreshScope
@RestController
 class MessageRestController {

    @Value("${message:Hello default}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }
}