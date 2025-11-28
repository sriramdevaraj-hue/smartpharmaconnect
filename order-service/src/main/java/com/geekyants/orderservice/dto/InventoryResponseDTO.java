package com.geekyants.orderservice.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class InventoryResponseDTO {

	private UUID id;
	private String name;
	private String category;
	private int stock;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
