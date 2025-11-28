package com.geekyants.orderservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.geekyants.common.events.OrderPlacedEvent;
import com.geekyants.orderservice.client.InventoryClient;
import com.geekyants.orderservice.dto.InventoryResponseDTO;
import com.geekyants.orderservice.dto.OrdersDTO;
import com.geekyants.orderservice.entity.Orders;
import com.geekyants.orderservice.entity.Status;
import com.geekyants.orderservice.repository.OrdersRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersRepository ordersRepo;
	
	@Autowired
	private InventoryClient invClient;
	
	@Autowired
	private RabbitTemplate template;
	

	public Orders addOrders(OrdersDTO dto) {
		
		 if (dto.getMedicineId() == null) {
		        throw new IllegalArgumentException("Medicine-ID cannot be Empty");
		    }
		Orders ord = new Orders();
		ord.setCustomerId(dto.getCustomerId());
		ord.setMedicineId(dto.getMedicineId());
		
		//Medicines med = invRepo.getById(dto.getMedicineId());
        //InventoryResponse inventory = invClient.getMedicineById(medicineId.toString());

        InventoryResponseDTO invRespDTO = invClient.getMedicinesDetails(dto.getMedicineId());

		if (null == invRespDTO) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Medicine not found");
		} else {
			if (dto.getQuantity() >= invRespDTO.getStock()) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Insufficient Stock");
				}
		}
		ord.setQuantity(dto.getQuantity());
		
		
		
		ord.setTotalPrice(dto.getTotalPrice());
		ord.setStatus(dto.getStatus());
		ord.setCreatedAt(LocalDateTime.now());
		Orders newOrders =  ordersRepo.save(ord);
		
		OrderPlacedEvent event = new OrderPlacedEvent();
        event.setOrderId(newOrders.getId());
        event.setCustomerId(newOrders.getCustomerId());
        event.setTotalAmount(new BigDecimal(newOrders.getTotalPrice()));
        event.setOrderedStock(newOrders.getQuantity());
        event.setMedicineId(newOrders.getMedicineId());
        
        template.convertAndSend("order.events", "order.placed", event);

        return newOrders;

	}

	public Orders getOrdersById(UUID id) {
		Optional<Orders> ord = ordersRepo.findById(id);
		if (ord.isPresent() && !ord.isEmpty()) {
			return ord.get();
		} else {
			throw new EntityNotFoundException();
		}
	}

	public Orders updateOrderStatus(UUID id, Status status) {
		Orders ord = getOrdersById(id);
		ord.setStatus(status);
		return ordersRepo.save(ord);
	}

}
