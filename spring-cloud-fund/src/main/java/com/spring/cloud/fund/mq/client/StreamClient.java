package com.spring.cloud.fund.mq.client;

import com.spring.cloud.fund.mq.constant.MqConstant;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
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
    MessageChannel output();

    /**
     *
     * 延迟队列输出
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Output(MqConstant.DELAY_OUT_PUT)
    MessageChannel delayOutput();

//
//    /**
//     *
//     * 延迟队列输入
//     * @return org.springframework.messaging.SubscribableChannel
//     */
//    @Input(MqConstant.DELAY_IN_PUT)
//    MessageChannel delayInput();


    /**
     *
     * 死信队列输入
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Input(MqConstant.DEAD_LETTER_IN_PUT)
    MessageChannel deadLetterInput();
}
