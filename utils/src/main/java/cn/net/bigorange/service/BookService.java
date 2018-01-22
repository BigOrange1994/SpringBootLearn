package cn.net.bigorange.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.boot.actuate.endpoint.mvc.HypermediaDisabled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by bigorange on 2018/1/16.
 */
@Service
public class BookService {

    private final RestTemplate restTemplate;

    public BookService(RestTemplate rest){
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String readingList() {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create("http://localhost:8090/recommended");
        return  restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Cloud Native Java (O'Reilly)";
    }

}
