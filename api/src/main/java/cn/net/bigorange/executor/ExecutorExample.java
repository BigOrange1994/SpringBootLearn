package cn.net.bigorange.executor;

import cn.net.bigorange.job.SimpleJob;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by bigorange on 2018/2/27.
 */
public class ExecutorExample {

    private Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void executeTasks(){
        for(int i = 0; i < 10; i++){
            executor.execute(new SimpleTask("task" + i));
        }
    }

    public static void main(String[] args) {
        ExecutorExample executorExample = new ExecutorExample();
        executorExample.setExecutor(Executors.newFixedThreadPool(5));
        executorExample.executeTasks();
    }

}

class SimpleTask implements Runnable{

    private String taskName;

    @Override
    public void run() {
        System.out.println("do task: " + taskName + " in Thread..." + Thread.currentThread().getId());
    }

    public SimpleTask(String taskName){
        this.taskName = taskName;
    }

}

