package com.rabbitmq.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHAL
 * 创建时间：2018/9/28 17:01
 */
@Component
@RabbitListener(queues="hello")
public class Consumer {
    @Resource
    AmqpTemplate template;

    @RabbitHandler
    public void consume(String str){
        System.out.println(str);
    }
}
