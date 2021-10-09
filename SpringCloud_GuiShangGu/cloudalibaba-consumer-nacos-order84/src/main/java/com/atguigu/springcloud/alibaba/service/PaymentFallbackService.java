package com.atguigu.springcloud.alibaba.service;

import com.wen.springcloud.entities.CommonResult;
import com.wen.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444444,"服务降级返回，----PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
