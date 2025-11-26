package com.geekyants.orderservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.geekyants.orderservice.entity.Status;

import lombok.Data;

@Data
public class OrdersDTO {

	private UUID id;
	private UUID customerId;
	private UUID medicineId;
	private int quantity;
	private int totalPrice;
	private Status status;
	private LocalDateTime createdAt;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public UUID getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(UUID medicineId) {
		this.medicineId = medicineId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
