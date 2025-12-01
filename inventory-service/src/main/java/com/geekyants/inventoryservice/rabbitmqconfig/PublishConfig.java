package com.geekyants.inventoryservice.rabbitmqconfig;

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

@Configuration
public class PublishConfig {

        public static final String QUEUE = "inventory.medicine.updated";
        public static final String EXCHANGE = "inventory.events";
        public static final String ROUTING_KEY = "medicine.updated";

        @Bean
        public Queue queue() {
            return new Queue(QUEUE);
        }
        @Bean
        public TopicExchange topicExchange() {
            return new TopicExchange(EXCHANGE);
        }
        @Bean
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

