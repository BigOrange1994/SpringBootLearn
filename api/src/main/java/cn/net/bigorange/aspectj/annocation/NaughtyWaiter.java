package cn.net.bigorange.aspectj.annocation;

import cn.net.bigorange.advice.Waiter;

/**
 * Created by bigorange on 2018/3/12.
 */
public class NaughtyWaiter implements Waiter{

    @NeedTest
    @Override
    public void greetingTo(String name) {
        System.out.println("NaughtyWaiter greeting to " + name + "...");
    }

    @Override
    public void serveTo(String name) {
        System.out.println("NaughtyWaiter serveTo to " + name + "...");
    }
}
