package com.geekyants.orderservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.orderservice.dto.OrdersDTO;
import com.geekyants.orderservice.entity.Orders;
import com.geekyants.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrdersController {

	@Autowired
	private OrderService orderServ;

	@PostMapping("/orders")
	public ResponseEntity<?> placeOrders(@RequestBody OrdersDTO dto) {
		try {
			orderServ.addOrders(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Order Placed Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Not Placed..!");
		}
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<?> viewOrders(@PathVariable("id") UUID id) {
		try {
			Orders ord = orderServ.getOrdersById(id);
			return ResponseEntity.status(HttpStatus.FOUND).body(ord);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Orders Present..!");
		}
	}
	
	@PatchMapping("/orders/{id}/status")
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateStatus(@PathVariable("id") UUID id, @RequestBody OrdersDTO dto) {
		try {
			orderServ.updateOrderStatus(id, dto.getStatus());
			return ResponseEntity.status(HttpStatus.FOUND).body("Updated Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Failed to Updated");
		}
	}

}
