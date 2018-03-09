package cn.net.bigorange.advice;

/**
 * Created by bigorange on 2018/3/9.
 */
public class NativeWaiterDelegate {

    private Waiter waiter;

    public void service(String clientName){
        waiter.serveTo(clientName);
        waiter.greetingTo(clientName);
    }

    public void setWaiter(Waiter waiter){
        this.waiter = waiter;
    }

}
