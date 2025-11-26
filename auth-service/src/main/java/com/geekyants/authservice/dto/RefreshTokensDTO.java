package com.geekyants.authservice.dto;

import lombok.Data;

@Data
public class RefreshTokensDTO {
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
