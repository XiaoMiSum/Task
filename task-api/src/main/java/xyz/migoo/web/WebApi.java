package xyz.migoo.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaomi
 */
@SpringBootApplication
@MapperScan(basePackages = "xyz.migoo.web.dao")
public class WebApi {
    public static void main(String[] args) {
        SpringApplication.run(WebApi.class);
    }
}
