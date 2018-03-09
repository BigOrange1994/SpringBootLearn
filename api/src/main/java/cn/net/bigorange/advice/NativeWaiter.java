package cn.net.bigorange.advice;

/**
 * Created by bigorange on 2018/1/25.
 */
public class NativeWaiter implements Waiter {


    @Override
    public void greetingTo(String name) {
        System.out.println("waiter greeting to " + name + "...");
    }

    @Override
    public void serveTo(String name) {
        System.out.println("waiter serve to " + name + "...");
    }

}
