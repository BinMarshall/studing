package com.rabbit.rabbit1.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2018/10/2 14:36
 */
@Component
public class SendQueue {
    @Bean
    public Queue queue(){
        return new Queue("hello");
    }
}
