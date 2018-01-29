package cn.net.bigorange.helper.jdkproxy;

import cn.net.bigorange.handler.PerformanceHandler;
import cn.net.bigorange.service.jdkproxy.ForumService;
import cn.net.bigorange.service.jdkproxy.impl.ForumServiceImpl;

import java.lang.reflect.Proxy;

/**
 * Created by bigorange on 2018/1/24.
 */
public class TestProxy {

    public static void main(String[] args) {
        ForumService service = new ForumServiceImpl();
        PerformanceHandler handler= new PerformanceHandler(service);
        ForumService proxy = (ForumService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(), handler);
        proxy.removeTopic(10);
        proxy.removeForum(1024);
    }

}
