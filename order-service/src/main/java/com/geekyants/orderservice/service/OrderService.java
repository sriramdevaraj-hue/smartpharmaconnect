package com.geekyants.orderservice.service;

import java.util.UUID;

import com.geekyants.orderservice.dto.OrdersDTO;
import com.geekyants.orderservice.entity.Orders;
import com.geekyants.orderservice.entity.Status;

public interface OrderService {

	Orders addOrders(OrdersDTO dto);

	Orders getOrdersById(UUID id);

	Orders updateOrderStatus(UUID id, Status status);

}
