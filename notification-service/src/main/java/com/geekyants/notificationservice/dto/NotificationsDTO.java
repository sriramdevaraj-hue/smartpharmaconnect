package com.geekyants.notificationservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class NotificationsDTO {

	private UUID id;
	private UUID refId;
	private String message;
	private LocalDateTime createdAt;

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getRefId() {
		return refId;
	}
	public void setRefId(UUID refId) {
		this.refId = refId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
