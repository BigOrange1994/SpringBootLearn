package cn.net.bigorange.lambda;

import java.util.function.Predicate;

/**
 * Created by bigorange on 2018/5/2.
 */
public class TestLambda {

    public static void main(String[] args) {
        Predicate p = (n) -> false ;

        p.test(p);
    }

}
