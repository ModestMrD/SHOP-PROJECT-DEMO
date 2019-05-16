package com.dq.product.background.config;

import com.dq.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DuQian
 * @Date 2019/3/18
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitMQConstant.BACKGROUND_EXCHANGE);
    }
}
