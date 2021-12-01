package com.spring.cloud.fund.mq.client;

import com.spring.cloud.fund.mq.constant.MqConstant;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author haozai
 * @date 2021/10-12 09:52
 */
public interface StreamClient {

    /**
     *
     * 消息输入
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Input(MqConstant.IN_PUT)
    SubscribableChannel input();

    /**
     *
     * 消息输出
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Output(MqConstant.OUT_PUT)
    SubscribableChannel output();

    /**
     *
     * 延迟队列输出
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Output(MqConstant.DELAY_OUT_PUT)
    SubscribableChannel delayOutput();


    /**
     *
     * 延迟队列输入
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Input(MqConstant.DELAY_IN_PUT)
    SubscribableChannel delayInput();


    /**
     *
     * DLQ队列输出
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Output(MqConstant.DLQ_OUT_PUT)
    SubscribableChannel dlqOutput();

    /**
     *
     * DLQ队列输入
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Input(MqConstant.DLQ_IN_PUT)
    SubscribableChannel dlqInput();

    /**
     *
     * 死信队列输出
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Output(MqConstant.DEAD_LETTER_OUT_PUT)
    SubscribableChannel deadLetterOutput();

    /**
     *
     * 死信队列输入
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Input(MqConstant.DEAD_LETTER_IN_PUT)
    SubscribableChannel deadLetterInput();
}
