package com.boot.mybootdemo2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*启动类 主类*/
@MapperScan(basePackages = {"com.mapper"})
@ComponentScan(basePackages = {"com.service","com.serviceImpl","com.serviceutil","com.controller"})
public class Mybootdemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybootdemo2Application.class, args);
    }

}
