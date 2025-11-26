package com.geekyants.common.events;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderPlacedEvent {
	
	private UUID orderId;
    private UUID customerId;
    private BigDecimal totalAmount;
    private String currency;
    
    
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
    
    


}
