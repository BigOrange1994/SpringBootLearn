package cn.net.bigorange.helper.resourcesloader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by think on 2017/12/25.
 */
@RestController
@RequestMapping(value = "test")
public class TestController {

    Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/test/modules")
    public String getDate(){
        return  "222";
    }

    @GetMapping("/resources")
    public void testResources(){

        String filePath = "C:\\Users\\think\\Desktop\\testPath.txt";
        // 以系统文件路径进行加载
        WritableResource rs1 = new PathResource(filePath);

        // 使用类路径（不包含classpath）加载配置文件
        Resource rs2 = new ClassPathResource("config/testPath.txt");

        System.out.println(rs1.getFilename());
        System.out.println(rs2.getFilename());

        // WritableResources 接口写资源文件
        try(OutputStream stream1 = rs1.getOutputStream()){
            stream1.write("欢迎光临".getBytes());
        }catch (IOException e){
            log.info("WritableResources 写入失败");
        }


        // 使用resources读取资源文件
        try (InputStream is1 = rs1.getInputStream()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i;
            while((i = is1.read())!= -1){
                baos.write(i);
            }
            System.out.print(baos.toString());
        } catch (IOException e){
            log.info("Resources 读取失败");
        }

       try {
            InputStream is2 = rs2.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}



