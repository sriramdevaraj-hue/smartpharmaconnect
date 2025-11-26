package com.geekyants.pricingservice.rabbitmqconfig;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PublishConfig {

    public static final String QUEUE = "pricing.pricing.updated";
    public static final String EXCHANGE = "pricing.events";
    public static final String ROUTING_KEY = "pricing.updated";

    @Bean
    @Primary
    public Queue queue() {
        return new Queue(QUEUE);
    }
    @Bean
    @Primary
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    @Primary
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }


	
	
	

}
