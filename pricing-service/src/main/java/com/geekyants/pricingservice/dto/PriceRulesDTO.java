package com.geekyants.pricingservice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PriceRulesDTO {

	private Long id;
	private Long medicineId;
	private Double finalPrice;
	private LocalDateTime calculatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public LocalDateTime getCalculatedAt() {
		return calculatedAt;
	}

	public void setCalculatedAt(LocalDateTime calculatedAt) {
		this.calculatedAt = calculatedAt;
	}

}
