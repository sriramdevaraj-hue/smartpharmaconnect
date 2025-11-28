package com.geekyants.inventoryservice.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
	
	
	public static final String QUEUE = "inventory.order.placed";
    public static final String EXCHANGE = "order.events";
    public static final String ROUTING_KEY = "order.placed";

	@Bean
	public Queue inventoryMedicineUpdatedQueue() {
		return QueueBuilder.durable(QUEUE).build();
	}

	@Bean
	public TopicExchange inventoryExchange() {
		return new TopicExchange(EXCHANGE, true, false);
	}

	@Bean
	public Binding bindInventoryUpdatedEvent() {
		return BindingBuilder.bind(inventoryMedicineUpdatedQueue()).to(inventoryExchange()).with(ROUTING_KEY);
	}

}
