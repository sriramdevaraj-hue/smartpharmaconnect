package com.geekyants.notificationservice.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

	
	@Bean(name = "userExchange")
    public FanoutExchange userEventsExchange() {
        return new FanoutExchange("user.events");
    }
	@Bean(name = "userRegQueue")
	public Queue userRegisteredNotificationQueue() {
		return QueueBuilder.durable("user.registered.notifications").build();
	}
	@Bean
	public Binding userRegisteredNotificationBinding(@Qualifier("userRegQueue") Queue userRegisteredNotificationQueue,
			@Qualifier("userExchange")FanoutExchange userEventsExchange) {
		return BindingBuilder.bind(userRegisteredNotificationQueue).to(userEventsExchange);
	}
	
	
	
	
	@Bean(name = "priceExchange")
    public TopicExchange pricingEventsExchange() {
        return new TopicExchange("pricing.events");
    }
	@Bean(name = "pricingUpdateQueue")
	public Queue pricingUpdatedNotificationQueue() {
		return QueueBuilder.durable("pricing.pricing.updated").build();
	}
	@Bean
	public Binding pricingUpdatedNotificationBinding(@Qualifier("pricingUpdateQueue") Queue pricingUpdatedNotificationQueue,
			@Qualifier("priceExchange")TopicExchange pricingEventsExchange) {
		return BindingBuilder.bind(pricingUpdatedNotificationQueue).to(pricingEventsExchange).with("pricing.updated");
	}
	
	
	

	@Bean(name = "orderExchange")
    public TopicExchange orderEventsExchange() {
        return new TopicExchange("order.events");
    }
	@Bean(name = "orderPlacedQueue")
	public Queue orderPlacedNotificationQueue() {
		return QueueBuilder.durable("notification.order.placed").build();
	}
	@Bean
	public Binding orderPlacedNotificationBinding(@Qualifier("orderPlacedQueue") Queue orderPlacedNotificationQueue,
			@Qualifier("orderExchange")TopicExchange orderEventsExchange) {
		return BindingBuilder.bind(orderPlacedNotificationQueue).to(orderEventsExchange).with("order.placed");
	}
	
	
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter);
		return template;
	}


}
