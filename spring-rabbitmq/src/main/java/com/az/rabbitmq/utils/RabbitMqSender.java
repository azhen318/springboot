package com.az.rabbitmq.utils;

import com.az.rabbitmq.entry.RabbitChange;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class RabbitMqSender implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    private Log logger= LogFactory.getLog(this.getClass());

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("confirm: " + correlationData.getId());
    }

    public void sendRabbitmqDirect(String routeKey,Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitChange.CONTRACT_DIRECT.getCode(), routeKey , obj, correlationData);
    }

}

