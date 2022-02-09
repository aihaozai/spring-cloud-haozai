package com.spring.cloud.fund.receiver;

import com.rabbitmq.client.Channel;
import spring.cloud.base.mq.starter.base.client.StreamClient;
import spring.cloud.base.mq.starter.base.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * @author haozai
 * @date 2021/10-12 09:52
 */
@Slf4j
@Component
@EnableBinding(value = {StreamClient.class})
public class StreamReceiver {

    @StreamListener(MqConstant.DELAY_IN_PUT)
    public void receiveDelay(Message message) throws IOException {
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        log.info("延迟接收到消息：{}" , message);
        if(deliveryTag!=null&&channel!=null){
            channel.basicAck(deliveryTag,true);
        }
    }

    @StreamListener(MqConstant.DLQ_IN_PUT)
    public void receiveDlq(Message message) throws IOException {
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        log.info("DLQ接收到消息：{}" , message);
        if(deliveryTag!=null&&channel!=null){

        }
        int num = 1/0;
    }

    @StreamListener(MqConstant.DEAD_LETTER_IN_PUT)
    public void receiveDeadLetter(Message message) throws IOException {
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        log.info("死信接收到消息：{}" , message);
        if(deliveryTag!=null&&channel!=null){
            channel.basicAck(deliveryTag,true);
        }
    }

}
