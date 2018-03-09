package cn.net.bigorange.advice;

/**
 * Created by bigorange on 2018/3/8.
 */
public class NativeSeller implements Seller{

    @Override
    public void greetingTo(String name) {
        System.out.println("seller greeting to " + name + "...");
    }

}
