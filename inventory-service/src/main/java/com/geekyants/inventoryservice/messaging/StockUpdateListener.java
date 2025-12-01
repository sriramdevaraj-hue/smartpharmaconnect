package com.geekyants.inventoryservice.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.geekyants.common.events.OrderPlacedEvent;
import com.geekyants.inventoryservice.service.InventoryServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StockUpdateListener {
	
	//private final InventoryServiceImpl invServiceImpl;

//    @RabbitListener(queues = "notification.order.placed")
//    public void handlePriceUpdated(OrderPlacedEvent event) {
//        //
//    	//log.info("Pricing received medicine.updated: {}", event);
//    	invServiceImpl.updateStock(event);
//    }

}





