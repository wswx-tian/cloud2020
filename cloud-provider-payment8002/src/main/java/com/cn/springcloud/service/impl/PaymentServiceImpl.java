package com.cn.springcloud.service.impl;


import com.cn.springcloud.dao.PaymentDao;
import com.cn.springcloud.entities.Payment;
import com.cn.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2020-02-18 10:40
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService
{
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment)
    {
        return paymentDao.create(payment);
    }

    @HystrixCommand(fallbackMethod ="weak_getPaymentById",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "6000")
    })
    public Payment getPaymentById(Long id)  {
        try {
            double random = Math.random();
            log.info("time is {}",random);
            TimeUnit.SECONDS.sleep((int)random);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return paymentDao.getPaymentById(id);
    }


    public Payment weak_getPaymentById(Long id)
    {
        Payment payment = new Payment();
        payment.setId(-1L);
        payment.setSerial("-9999");
        return payment;
    }
}
