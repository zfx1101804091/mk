package com.example.mk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mk.mapper")
public class MkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MkApplication.class, args);
    }

}
