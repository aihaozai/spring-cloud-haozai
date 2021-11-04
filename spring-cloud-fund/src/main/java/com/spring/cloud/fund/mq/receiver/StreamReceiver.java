package com.spring.cloud.fund.mq.receiver;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.spring.cloud.fund.mq.client.StreamClient;
import com.spring.cloud.fund.mq.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
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


    @StreamListener(MqConstant.IN_PUT)
    public void receive(Message message) throws IOException {
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        log.info("接收到消息：{}" , message);
        if(deliveryTag!=null&&channel!=null){
            channel.basicAck(deliveryTag,true);
        }
    }
}
