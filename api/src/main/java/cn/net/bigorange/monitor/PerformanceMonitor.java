package cn.net.bigorange.monitor;

import cn.net.bigorange.performance.MethodPerformance;

/**
 * Created by bigorange on 2018/1/24.
 */
public class PerformanceMonitor {

    private static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<MethodPerformance>();

    public static void begin(String method){
        System.out.println("beign monitor");
        MethodPerformance mp = new MethodPerformance(method);
        performanceRecord.set(mp);
    }

    public static void end(){
        System.out.println("end monitor");
        MethodPerformance mp = performanceRecord.get();
        mp.printPerformance();
    }

}
