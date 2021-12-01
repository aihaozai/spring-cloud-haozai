package com.spring.cloud.fund.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author zhenjh
 * @date 2021-11-29  15:11
 * @description 消息发送成功的回调
 */
@Slf4j
public class RabbitConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息唯一标识: {}", correlationData);
        log.info("确认状态: {}", ack);
        log.info("造成原因: {}", cause);
    }
}
