package com.geekyants.orderservice.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekyants.orderservice.dto.OrdersDTO;
import com.geekyants.orderservice.entity.Orders;
import com.geekyants.orderservice.entity.Status;
import com.geekyants.orderservice.repository.OrdersRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersRepository ordersRepo;

	public Orders addOrders(OrdersDTO dto) {
		Orders ord = new Orders();
		ord.setCustomerId(dto.getCustomerId());
		ord.setMedicineId(dto.getMedicineId());
		ord.setQuantity(dto.getQuantity());
		ord.setTotalPrice(dto.getTotalPrice());
		ord.setStatus(dto.getStatus());
		ord.setCreatedAt(LocalDateTime.now());
		return ordersRepo.save(ord);
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
