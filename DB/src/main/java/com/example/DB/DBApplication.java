package com.example.DB;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.DB.*")
public class DBApplication {

    public static void main(String[] args) {
        SpringApplication.run(DBApplication.class, args);
    }

}
