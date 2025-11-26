package com.geekyants.notificationservice.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConsumerConfig {

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

	@Bean
	
	public DirectExchange userEventsExchange() {
		return new DirectExchange("user.events");
	}

	@Bean
	public DirectExchange pricingEventsExchange() {
		return new DirectExchange("pricing.events");
	}

	@Bean
	@Primary
	public DirectExchange orderEventsExchange() {
		return new DirectExchange("order.events");
	}
	
	
	

	@Bean
	public Queue userRegisteredNotificationQueue() {
		return QueueBuilder.durable("user.registered.notifications").build();
	}

	@Bean
	public Queue pricingUpdatedNotificationQueue() {
		return QueueBuilder.durable("pricing.pricing.updated").build();
	}

	@Bean
	@Primary
	public Queue orderPlacedNotificationQueue() {
		return QueueBuilder.durable("notification.order.placed").build();
	}
	
	
	
	

	@Bean
	public Binding userRegisteredNotificationBinding(Queue userRegisteredNotificationQueue,
			DirectExchange userEventsExchange) {
		return BindingBuilder.bind(userRegisteredNotificationQueue).to(userEventsExchange).with("user.registered");
	}

	@Bean
	public Binding pricingUpdatedNotificationBinding(Queue pricingUpdatedNotificationQueue,
			DirectExchange pricingEventsExchange) {
		return BindingBuilder.bind(pricingUpdatedNotificationQueue).to(pricingEventsExchange).with("pricing.updated");
	}

	@Bean
	@Primary
	public Binding orderPlacedNotificationBinding(Queue orderPlacedNotificationQueue,
			DirectExchange orderEventsExchange) {
		return BindingBuilder.bind(orderPlacedNotificationQueue).to(orderEventsExchange).with("order.placed");
	}

}
