package cn.net.bigorange.helper.cglibproxy;

import cn.net.bigorange.service.jdkproxy.ForumService;
import cn.net.bigorange.service.jdkproxy.impl.ForumServiceImpl;

/**
 * Created by bigorange on 2018/1/24.
 */
public class TestCglibProxy {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl serviceImpl = (ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);
        serviceImpl.removeTopic(20);
        serviceImpl.removeForum(40);
    }

}
