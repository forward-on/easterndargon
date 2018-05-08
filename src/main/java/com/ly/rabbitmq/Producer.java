package com.ly.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author : ly.
 * @Date : 2018/5/8.
 */
@Service
public class Producer {

    private Logger logger = LoggerFactory.getLogger(Producer.class);

    @Resource(name="amqpTemplate")
    private AmqpTemplate amqpTemplate;

    @Resource(name="amqpTemplate2")
    private AmqpTemplate amqpTemplate2;

    public void sendMessage(Object message) throws IOException {
        logger.info("to send message:{}", message);
        amqpTemplate.convertAndSend("queueTestKey", message);
        amqpTemplate.convertAndSend("queueTestTwo", message);
        amqpTemplate2.convertAndSend("shijj.xxxx.wsdwd", message);
    }

}
