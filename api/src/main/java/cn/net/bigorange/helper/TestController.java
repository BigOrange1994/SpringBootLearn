package cn.net.bigorange.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by think on 2017/12/25.
 */
@RestController
@RequestMapping(value = "test")
public class TestController {


    @GetMapping("/test/modules")
    public String getDate(){
        return  "222";
    }

    @GetMapping("/resources")
    public void testResources(){

        String filePath = "C:\\Users\\think\\Desktop\\testPath.txt";
        // 以系统文件路径进行加载
        WritableResource rs1 = new PathResource(filePath);

        // 使用类路径加载配置文件
        Resource rs2 = new ClassPathResource("resources/config/testPath.txt");

        System.out.println(rs1.getFilename());
        System.out.println(rs2.getFilename());

        // WritableResources 接口写资源文件


    }


}



