package com.az.rabbitmq.config;

import com.az.rabbitmq.entry.RabbitRouteEnum;
import com.az.rabbitmq.entry.RabbitChange;
import com.az.rabbitmq.entry.RabbitQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqChangeConfig {

    private Log logger= LogFactory.getLog(RabbitMqChangeConfig.class);
    @Bean
    Queue queueTest(RabbitAdmin rabbitAdmin){
        Queue queue=new Queue(RabbitQueue.TESTQUEUE.getCode());
        rabbitAdmin.declareQueue(queue);
        logger.debug("queueTest 实例化成功");
        return queue;
    }

    @Bean
    DirectExchange testDirectExchange(RabbitAdmin rabbitAdmin){
        DirectExchange contractDirectExchange=
                new DirectExchange(RabbitChange.CONTRACT_DIRECT.getCode());
        rabbitAdmin.declareExchange(contractDirectExchange);
        logger.debug("testDirectExchange 实例化成功");
        return contractDirectExchange;
    }

    @Bean
    Binding bingQueueTest(RabbitAdmin rabbitAdmin,@Qualifier("queueTest") Queue queueTest,
                                DirectExchange directExchange){
        Binding binding= BindingBuilder.bind(queueTest).to(directExchange)
                .with(RabbitRouteEnum.TESTQUEUE.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列和路由绑定成功");
        return binding;

    }



}
