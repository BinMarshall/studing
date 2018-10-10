package com.rabbitmq.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHAL
 * 创建时间：2018/9/28 17:00
 */
@RestController
public class Producer {
    @Resource
    AmqpTemplate template;

    @RequestMapping("/produce")
    public String produce(){
        String context = "hello I am a producer : " + new Date();
        template.convertAndSend("hello", context);
        return context;
    }
}
