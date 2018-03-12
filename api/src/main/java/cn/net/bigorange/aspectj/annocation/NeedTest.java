package cn.net.bigorange.aspectj.annocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by bigorange on 2018/3/12.
 */
// 声明注解的保留期限
@Retention(RetentionPolicy.RUNTIME)
// 声明可以使用该注解的目标类型
@Target(ElementType.METHOD)
// 定义注解
public @interface NeedTest {

    // 声明注解成员
    boolean value() default true;

}
