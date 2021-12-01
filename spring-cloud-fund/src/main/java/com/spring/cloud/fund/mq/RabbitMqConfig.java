package com.spring.cloud.fund.mq;


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhenjh
 * @date 2021-11-29
 * @description <p></p>
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        CachingConnectionFactory factory = (CachingConnectionFactory) connectionFactory;
        factory.addConnectionListener(connection -> System.err.println("端口：" + connection.getLocalPort()));

        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setConfirmCallback(new RabbitConfirmCallBack());
        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitReturnCallback());
        return rabbitTemplate;
    }
}
