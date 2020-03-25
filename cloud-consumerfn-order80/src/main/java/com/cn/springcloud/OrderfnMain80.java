package com.cn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderfnMain80 {
         public static void main(String[] args) {
                       SpringApplication.run(OrderfnMain80.class, args);
                   }
}
