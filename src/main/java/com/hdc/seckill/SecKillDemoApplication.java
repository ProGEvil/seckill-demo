package com.hdc.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hdc.seckill.mapper")
public class SecKillDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecKillDemoApplication.class, args);
    }

}
