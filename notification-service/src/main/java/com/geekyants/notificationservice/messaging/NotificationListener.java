package com.geekyants.notificationservice.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geekyants.common.events.OrderPlacedEvent;
import com.geekyants.common.events.PricingUpdatedEvent;
import com.geekyants.common.events.UserRegisteredEvent;
import com.geekyants.notificationservice.service.NotificationsService;

@Component
public class NotificationListener {
	
	@Autowired
    private NotificationsService notificationsService;

    @RabbitListener(queues = "user.registered.notifications")
    public void handleUserRegistered(UserRegisteredEvent event) {
        notificationsService.createNotificationForUser(
                event.getUserId(),
                "Welcome to SmartPharma!!! "+ event.getEmail()+" Your account has been created successfully."
        );
    }

    @RabbitListener(queues = "pricing.pricing.updated")
    public void handlePricingUpdated(PricingUpdatedEvent event) {
        notificationsService.createBroadcastNotification(
                event.getMedicineId(),
                "New price for medicine " + event.getMedicineId() + " is " +
                        event.getNewPrice() + "  updated successfully...!"
        );
    }

    @RabbitListener(queues = "notification.order.placed")
    public void handleOrderPlaced(OrderPlacedEvent event) {
        notificationsService.createNotificationForUser(
                event.getCustomerId(),
                "Order placed ! Your order " + event.getOrderId() + " has been placed, total " +
                        event.getTotalAmount() + "."
        );
    }


}
