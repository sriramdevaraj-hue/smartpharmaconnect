package com.geekyants.common.events;

import java.util.UUID;

import lombok.Data;

@Data
public class PricingUpdatedEvent {
	private UUID medicineId;
    private Double newPrice;
	public UUID getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(UUID medicineId) {
		this.medicineId = medicineId;
	}
	public Double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(Double double1) {
		this.newPrice = double1;
	}
    
    
}
