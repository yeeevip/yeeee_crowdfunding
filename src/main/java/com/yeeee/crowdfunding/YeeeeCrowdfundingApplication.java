package com.yeeee.crowdfunding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yeeee.crowdfunding.mapper")
public class YeeeeCrowdfundingApplication {

    public static void main(String[] args) {
        SpringApplication.run(YeeeeCrowdfundingApplication.class, args);
    }

}
