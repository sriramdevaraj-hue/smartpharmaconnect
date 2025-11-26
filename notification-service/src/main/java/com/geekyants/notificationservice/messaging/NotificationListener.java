package com.geekyants.notificationservice.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.geekyants.common.events.MedicineUpdatedEvent;
import com.geekyants.common.events.OrderPlacedEvent;
import com.geekyants.common.events.PricingUpdatedEvent;
import com.geekyants.common.events.UserRegisteredEvent;
import com.geekyants.notificationservice.service.NotificationsService;


public class NotificationListener {
	
	@Autowired
    private NotificationsService notificationsService;

    @RabbitListener(queues = "user.registered.notifications")
    public void handleUserRegistered(UserRegisteredEvent event) {
        //log.info("Notification: user.registered {}", event);
        notificationsService.createNotificationForUser(
                event.getUserId(),
                "Welcome to SmartPharma",
                "Your account has been created successfully."
        );
    }

    @RabbitListener(queues = "medicine.updated.notifications")
    public void handleMedicineUpdated(MedicineUpdatedEvent event) {
        //log.info("Notification: medicine.updated {}", event);
        notificationsService.createBroadcastNotification(
                event.getMedicineID(),
                "Medicine " + event.getName() + " has been updated in inventory."
        );
    }

    @RabbitListener(queues = "pricing.updated.notifications")
    public void handlePricingUpdated(PricingUpdatedEvent event) {
        //log.info("Notification: pricing.updated {}", event);
        notificationsService.createBroadcastNotification(
                event.getMedicineId(),
                "New price for medicine " + event.getMedicineId() + " is " +
                        event.getNewPrice() + "."
        );
    }

    @RabbitListener(queues = "order.placed.notifications")
    public void handleOrderPlaced(OrderPlacedEvent event) {
       // log.info("Notification: order.placed {}", event);
        notificationsService.createNotificationForUser(
                event.getCustomerId(),
                "Order placed",
                "Your order " + event.getOrderId() + " has been placed, total " +
                        event.getTotalAmount() + "."
        );
    }


}
