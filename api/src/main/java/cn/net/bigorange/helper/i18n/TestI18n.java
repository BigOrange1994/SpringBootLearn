package cn.net.bigorange.helper.i18n;


import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by bigorange on 2018/1/22.
 */
public class TestI18n {


    public static void main(String[] args) throws Exception {
        testJDKUtilLocal();
        testResourceBundleMessageResources();
        testResourceBundle();
        testReloadableSource();
        testSpringMessageSource();
    }

    public static void testJDKUtilLocal(){
        // 带语言、国家和地区
        Locale locale1 = new Locale("zh", "CN");
        Locale locale2 = new Locale("zh");
        // 等同于zh和CN
        Locale locale3 = Locale.CHINA;
        // 等同于zn
        Locale locale4 = Locale.CHINESE;
        // 获取本地的Locale
        Locale locale5 = Locale.getDefault();


        // NumberFormat
        NumberFormat format = NumberFormat.getCurrencyInstance(locale1);
        double d = 12345.67;
        System.out.println("NumberFormat： " + format.format(d));

        // DateFormat
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);
        Date date = new Date();
        System.out.println("DateFormat: " + dateFormat.format(date));

        // JDK1.8 提供的日期类
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
        dateTimeFormatter.withLocale(Locale.CANADA);
        System.out.println("DateTimeFormat: " + dateTimeFormatter.format(localDate) + dateTimeFormatter.getLocale().toString());

        // SimpleDateFormat 是DateFormat的子类
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年");

    }

    // Spring提供的MessageSource
    public static void testResourceBundleMessageResources(){
        String[] config = {"config/i18n/spring-i18n.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        // 获取Bean
        MessageSource ms = (MessageSource)ctx.getBean("myResources");
        Object[] params = {"John", new GregorianCalendar().getTime()};
        // 获取格式化的国际化信息
        String s1 = ms.getMessage("greeting.common",params, Locale.US);
        String s2 = ms.getMessage("greeting.morning",params, Locale.CHINA);
        String s3 = ms.getMessage("greeting.afternoon",params, Locale.CHINA);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    // JDK 提供提供的ResourceBundle
    public static void testResourceBundle(){
        // 注意这里的国际化配置文件的名称不能包含多余的“ . ”，如：i18n.resource.properties, 这个文件名会有问题
        ResourceBundle rb1 = ResourceBundle.getBundle("properties/i18n/i18nresource",Locale.US);
        ResourceBundle rb2 = ResourceBundle.getBundle("properties/i18n/i18nresource",Locale.CHINA);
        Object[] params = {"John", new GregorianCalendar().getTime()};
        String s1 = new MessageFormat(rb1.getString("greeting.common"), Locale.US).format(params);
        String s2 = new MessageFormat(rb2.getString("greeting.morning"), Locale.CHINA).format(params);
        String s3 = new MessageFormat(rb2.getString("greeting.afternoon"), Locale.CHINA).format(params);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    // Spring 提供的ReloadableResourceBundleMessageSource
    public static void testReloadableSource() throws InterruptedException {
        String[] config = {"config/i18n/spring-i18n.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        // 获取Bean
        MessageSource ms = (MessageSource)ctx.getBean("myResources");
        Object[] params = {"John", new GregorianCalendar().getTime()};
        for(int i = 0; i < 2; i++){
            String s1 = ms.getMessage("greeting.common", params, Locale.US);
            System.out.println("第" + i +"次加载配置：" + s1);
            Thread.currentThread().sleep(10000);
        }
    }

    // 声明容器级国际化信息资源 SpringMessageSource
    public static void testSpringMessageSource() {
        String[] config = {"config/i18n/spring-i18n.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Object[] params = {"John", new GregorianCalendar().getTime()};
        String str1 = ctx.getMessage("greeting.common", params, Locale.US);
        System.out.println("Spring容器级别国际化信息：" + str1);
    }

}
