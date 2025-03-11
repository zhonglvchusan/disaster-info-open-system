package wang.zehui.disasterinfoopensystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan//注入自定义过滤器
@MapperScan("wang.zehui.disasterinfoopensystem.dao")
public class DisasterInfoOpenSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisasterInfoOpenSystemApplication.class, args);
    }

}
