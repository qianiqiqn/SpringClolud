package com.wen.springcloud.controller;

import com.wen.springcloud.entities.CommonResult;
import com.wen.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
   // public static final String PAYMENT_URL="http://localhost:8001";单机版
   public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
  /*  RestTemplate 是从 Spring3.0 开始支持的一个 HTTP 请求工具，
     它提供了常见的REST请求方案的模版，例如 GET 请求、POST 请求、PUT 请求、
     DELETE 请求以及一些通用的请求执行方法 exchange 以及 execute  */
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){

        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        //使用getForObject
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }


    //getForEntity方法
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            log.info(entity.getStatusCode()+"\t  ");
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

}
