package com.geekyants.pricingservice.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

	public static final String INVENTORY_EXCHANGE = "inventory.events";
	public static final String INVENTORY_ROUTING_KEY = "medicine.updated";
	public static final String INVENTORY_QUEUE = "inventory.medicine.updated";

	@Bean
	public Queue inventoryMedicineUpdatedQueue() {
		return QueueBuilder.durable(INVENTORY_QUEUE).build();
	}

	@Bean
	public TopicExchange inventoryExchange() {
		return new TopicExchange(INVENTORY_EXCHANGE, true, false);
	}

	@Bean
	public Binding bindInventoryUpdatedEvent() {
		return BindingBuilder.bind(inventoryMedicineUpdatedQueue()).to(inventoryExchange()).with(INVENTORY_ROUTING_KEY);
	}
	
	@Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
	

}
