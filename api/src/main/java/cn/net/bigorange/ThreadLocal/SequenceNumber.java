package cn.net.bigorange.ThreadLocal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by bigorange on 2018/3/26.
 */
public class SequenceNumber extends NumberParent{

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };

    public int getNextNum(){
        seqNum.set(seqNum.get() + 1);
        System.out.println(seqNum.get());
        return  seqNum.get();
    }

    public void B(String str){

    }


    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();
        TestClient testClient1 = new TestClient(sn);
        TestClient testClient2 = new TestClient(sn);
        TestClient testClient3 = new TestClient(sn);
        testClient1.start();
        testClient2.start();
        testClient3.start();
        //Class c = sn.getClass();
        Class c = null;
        try {
            c = Class.forName("cn.net.bigorange.ThreadLocal.SequenceNumber");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        SequenceNumber s = null;

        try {
            //Class clazz  = classLoader.loadClass("cn.net.bigorange.ThreadLocal.SequenceNumber");
            Class clazz  = Class.forName("cn.net.bigorange.ThreadLocal.SequenceNumber");
            s = (SequenceNumber)clazz.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Class[] cArg = new Class[1];
            cArg[0] = String.class;
            Method method = c.getMethod("getA", String.class);
            //Method testMethod = c.getMethod("getA");
            System.out.println(method + "------");
            try {
                method.invoke(s, (Object[])null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private static class TestClient extends Thread{

        private SequenceNumber sn;

        public TestClient(SequenceNumber sn){
            this.sn = sn;
        }

        @Override
        public void run() {
            super.run();
            for(int i = 0; i < 3; i++){
                System.out.println(Thread.currentThread().getName() + ", " + sn.getNextNum());
            }
        }
    }

}
