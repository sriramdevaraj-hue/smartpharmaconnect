package com.geekyants.common.events;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderPlacedEvent {
	
	private UUID orderId;
    private UUID customerId;
    private BigDecimal totalAmount;
    private int orderedStock;
    private UUID medicineId;
    
    
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	public UUID getCustomerId() {
		return customerId;
	}
	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getOrderedStock() {
		return orderedStock;
	}
	public void setOrderedStock(int orderedStock) {
		this.orderedStock = orderedStock;
	}
	public UUID getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(UUID medicineId) {
		this.medicineId = medicineId;
	}
	

}
