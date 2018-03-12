package cn.net.bigorange.aspectj.annocation;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by bigorange on 2018/3/12.
 */
@Aspect
public class TestAspect {

    @AfterReturning("@annotation(cn.net.bigorange.aspectj.annocation.NeedTest)")
    public void needTestRun(){
        System.out.println("needTestRun() executed!");
    }

}
