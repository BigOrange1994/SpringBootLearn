package cn.net.bigorange.base;

/**
 * Created by bigorange on 2018/5/2.
 */
public class TestInheritanceThreadLocal {

    private static volatile InheritableThreadLocal<String> inheritableThreadLocal  = new InheritableThreadLocal<String>(){
        @Override
        protected String childValue(String parentValue) {
            return "childValue";
        }

        @Override
        protected String initialValue() {
            return "parentValue";
        }
    };

    public static void main(String[] args) {
        Runnable parentRun = () -> {
            String result = "";
            result = inheritableThreadLocal.get();
            System.out.println("parent Thread" + result);
            Runnable childRun  = () -> {
                String childReult = inheritableThreadLocal.get();
                System.out.println("child Thread" + childReult);
            };
            Thread childThread = new Thread(childRun);
            childThread.start();
        };
        Thread parentThread = new Thread(parentRun);
        parentThread.start();
    }


}
