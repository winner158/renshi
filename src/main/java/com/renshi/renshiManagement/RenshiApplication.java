package com.renshi.renshiManagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.renshi.renshiManagement.dao")
@SpringBootApplication
public class RenshiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenshiApplication.class, args);
    }

}
