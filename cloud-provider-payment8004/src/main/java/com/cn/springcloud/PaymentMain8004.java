package com.cn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//用于向zookeeper或者consl注册
public class PaymentMain8004 {
         public static void main(String[] args) {
                       SpringApplication.run(PaymentMain8004.class, args);
                   }
}
