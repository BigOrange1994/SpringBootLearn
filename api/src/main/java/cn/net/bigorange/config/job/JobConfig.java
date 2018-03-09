package cn.net.bigorange.config.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by bigorange on 2018/2/26.
 */
@Configuration
@ImportResource(locations = {"classpath:config/job/spring-quartz.xml"})
public class JobConfig {

}
