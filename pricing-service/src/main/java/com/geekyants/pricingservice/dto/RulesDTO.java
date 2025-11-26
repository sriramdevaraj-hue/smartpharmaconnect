package com.geekyants.pricingservice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RulesDTO {

	private Long id;
	private Double basePrice;
	private Double discountPercent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

}