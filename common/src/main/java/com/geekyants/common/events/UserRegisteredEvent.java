package com.geekyants.common.events;

import java.util.UUID;

import lombok.Data;

@Data
public class UserRegisteredEvent {
	
	private UUID userId;
    private String email;
    private String role;
    
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
   


}
