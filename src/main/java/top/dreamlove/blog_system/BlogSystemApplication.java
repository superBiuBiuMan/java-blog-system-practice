package top.dreamlove.blog_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.dreamlove.blog_system.mapper")
public class BlogSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSystemApplication.class, args);
    }

}
