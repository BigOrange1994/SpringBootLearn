package cn.net.bigorange.helper.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Created by bigorange on 2018/1/16.
 */
public class SayHelloConfiguration {

    @Autowired
    private IClientConfig ribbonClientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig config){
     return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig config){
        return new AvailabilityFilteringRule();
    }

}
