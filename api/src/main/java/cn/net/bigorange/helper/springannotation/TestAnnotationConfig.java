package cn.net.bigorange.helper.springannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bigorange on 2018/1/18.
 */
public class TestAnnotationConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.refresh();
    }



}
