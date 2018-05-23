package cn.net.bigorange.base;

/**
 * Created by bigorange on 2018/5/3.
 */
public class TestThread {

    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        t1.run();
        t1.getName();
    }

}



