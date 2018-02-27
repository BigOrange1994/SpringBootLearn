package cn.net.bigorange.executor;

import java.util.concurrent.Executor;

/**
 * Created by bigorange on 2018/2/27.
 */
public class SimpleExecutor implements Executor{

    @Override
    public void execute(Runnable command) {
        command.run();


    }

}
