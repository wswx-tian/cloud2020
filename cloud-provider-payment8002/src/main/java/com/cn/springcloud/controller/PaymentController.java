package com.cn.springcloud.controller;


import com.cn.springcloud.entities.CommonResult;
import com.cn.springcloud.entities.Payment;
import com.cn.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2020-02-18 10:43
 */
@RestController
@Slf4j
public class PaymentController
{
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;
    @PostMapping(value = "/payment/create")
    @ResponseBody
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("port{}插入结果{}",port,result);
        if(result>0){
            return new CommonResult(200,port+"success",result);
        }
        return new CommonResult(500,"faild", result);
    }

    @GetMapping(value = "/payment/get/{id}")
    @ResponseBody
    public CommonResult getPaymentId(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("port{}获得结果{}",port,paymentById);
        if(paymentById!=null){
            return new CommonResult(200,port+"success",paymentById);
        }
        return new CommonResult(500,"faild", null);
    }

    @GetMapping(value = "/payment/discovery")
    @ResponseBody
    public DiscoveryClient getDiscoveryClient(){
        List<String> services = discoveryClient.getServices();
        for (String x:services) {
            log.info(x);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        for (ServiceInstance x: instances) {
            log.info("getHost "+x.getHost());
            log.info("getInstanceId "+x.getInstanceId());
            log.info("getPort "+x.getPort());
            log.info("getUri "+x.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/timeout")
    @ResponseBody
    public String testTimeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return port+"返回结果";
    }
}
