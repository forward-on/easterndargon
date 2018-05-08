package com.ly.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author : ly.
 * @Date : 2018/5/8.
 */
public class ConsumerTwo implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(ConsumerOne.class);

    @Override
    public void onMessage(Message message) {
        logger.info("ConsumerTwo receive message------->:{}", message);

    }
}
