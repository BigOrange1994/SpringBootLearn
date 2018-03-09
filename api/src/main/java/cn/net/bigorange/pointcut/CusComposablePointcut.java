package cn.net.bigorange.pointcut;

import cn.net.bigorange.advice.NativeWaiterDelegate;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * Created by bigorange on 2018/3/9.
 */
public class CusComposablePointcut {

    public Pointcut getInterSectionPointCut(){
        ComposablePointcut composablePointcut = new ComposablePointcut();
        Pointcut pointcut = new ControlFlowPointcut(NativeWaiterDelegate.class, "service");
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.addMethodName("greetingTo");
        return composablePointcut.intersection(pointcut).intersection((Pointcut) nameMatchMethodPointcut);
    }

}
