package com.geekyants.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.geekyants.authservice.dto.LoginDTO;
import com.geekyants.authservice.dto.RefreshTokensDTO;
import com.geekyants.authservice.dto.UserDTO;
import com.geekyants.authservice.entity.User;
import com.geekyants.authservice.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userServ;

	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@RequestBody UserDTO dto) {
		try {
			User u = userServ.registerUser(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration Failed !");
		}
	}

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
		try {
			String token = userServ.loginUser(loginDTO);
			return ResponseEntity.ok(token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login Failed..!");
		}
	}

	@PostMapping("/auth/refresh")
	public ResponseEntity<?> refreshTokens(@RequestBody RefreshTokensDTO dto) {
		try {
			String tok = userServ.tokensRefresh(dto);
			return ResponseEntity.ok(tok);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	

}
