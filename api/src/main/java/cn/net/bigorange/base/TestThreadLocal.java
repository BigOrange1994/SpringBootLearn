package cn.net.bigorange.base;

/**
* @author zhangpengcheng@dong-xu.com
* @version ����ʱ�䣺2018��5��2�� ����10:38:33
* 
*/
public class TestThreadLocal {

    public static volatile ThreadLocal<String> userId = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "initialValue";
        }
    };

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                String result = "";
                result = userId.get();
                userId.set("aa");
                System.out.println(result);
            }
        };
        Thread t1 = new Thread(r1);
        t1.run();
    }


    
}
