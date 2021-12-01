package com.spring.cloud.fund.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author haozai
 * @date 2021-11-29  15:11
 * @description 发生异常时的消息返回提醒
 */
@Slf4j
public class RabbitReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息主体: {}", message);
        log.info("回复编码: {}", replyCode);
        log.info("回复内容: {}", replyText);
        log.info("交换器: {}", exchange);
        log.info("路由键: {}", routingKey);
    }
}