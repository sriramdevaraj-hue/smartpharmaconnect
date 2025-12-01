package com.geekyants.pricingservice.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.geekyants.common.events.MedicineUpdatedEvent;
import com.geekyants.pricingservice.service.PriceServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MedicineUpdateListener {
	
	private final PriceServiceImpl pricingService;

    @RabbitListener(queues = "inventory.medicine.updated")
    public void handleMedicineUpdated(MedicineUpdatedEvent event) {
        pricingService.calculatePrice(event);
    }
}


