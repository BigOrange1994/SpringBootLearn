package cn.net.bigorange.executor;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by bigorange on 2018/2/27.
 */
public class SpringExecutorExample {

    // Spring 提供的Executor
    private TaskExecutor executor;

    public void setExecutor(TaskExecutor executor) {
        this.executor = executor;
    }

    public void executeTasks(){
        for(int i = 0; i < 10; i++){
            executor.execute(new SimpleTask1("task" + i));
        }
    }

    public static void main(String[] args) {
        SpringExecutorExample executorExample = new SpringExecutorExample();
        executorExample.setExecutor(new SimpleAsyncTaskExecutor());
        executorExample.executeTasks();
    }

}


class SimpleTask1 implements Runnable{

    private String taskName;

    @Override
    public void run() {
        System.out.println("do task: " + taskName + " in Thread..." + Thread.currentThread().getId());
    }

    public SimpleTask1(String taskName){
        this.taskName = taskName;
    }

}

