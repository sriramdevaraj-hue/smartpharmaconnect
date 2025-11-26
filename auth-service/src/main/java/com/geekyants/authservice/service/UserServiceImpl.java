package com.geekyants.authservice.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.geekyants.authservice.dto.LoginDTO;
import com.geekyants.authservice.dto.RefreshTokensDTO;
import com.geekyants.authservice.dto.UserDTO;
import com.geekyants.authservice.entity.RefreshTokens;
import com.geekyants.authservice.entity.User;
import com.geekyants.authservice.repository.RefreshTokensRepository;
import com.geekyants.authservice.repository.UserRepository;
import com.geekyants.common.events.UserRegisteredEvent;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RefreshTokensRepository refTokRepo;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired
	private RabbitTemplate template;
	
	

	public User registerUser(UserDTO dto) {
		User u = new User();
		u.setEmail(dto.getEmail());
		u.setPasswordHash(pwdEncoder.encode(dto.getPassword()));
		u.setFullName(dto.getFullName());
		u.setRole(dto.getRole());
		u.setCreatedAt(LocalDateTime.now());
		User u1 = userRepo.save(u);

		UserRegisteredEvent event = new UserRegisteredEvent();
		event.setUserId(u1.getId());
		event.setEmail(u1.getEmail());
		event.setRole(u1.getRole().name());
		template.convertAndSend("user.events", "user.registered", event);
		return userRepo.save(u);
	}

	public String loginUser(LoginDTO loginDTO) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		User u = userRepo.findByEmail(loginDTO.getEmail()).orElseThrow();
		String jwtToken = JWTService.generateToken(Map.of("role", u.getRole().name()), u);

		RefreshTokens tok = new RefreshTokens();
		tok.setToken(jwtToken);
		tok.setUser(u);
		tok.setExpiresAt(JWTService.extractExpiration(jwtToken).toInstant());
		refTokRepo.save(tok);

		return jwtToken;
	}

//	public String tokensRefresh(RefeshTokensDTO dto) {
//		
//		//public String tokensRefresh(String authorizationHeader, String email)
//		String jwtToken = "";
//		if (JWTService.isTokenExpired(authorizationHeader)) {
//			//String username = JWTService.extractUsername(authorizationHeader);
//			User u = userRepo.findById(id).orElseThrow();
//			//User u = userRepo.findByEmail(email).orElseThrow();
//			jwtToken = JWTService.generateToken(u);
//			RefreshTokens tok = new RefreshTokens();
//			tok.setToken(jwtToken);
//			tok.setUserId(u.getId());
//			tok.setExpiresAt(JWTService.extractExpiration(jwtToken));
//			refTokRepo.save(tok);
//			// throw new RuntimeException("Refresh token is expired");
//		} else {
//		 throw new RuntimeException("Token is already active");
//		}
//		return jwtToken;
//	}

	public String tokensRefresh(RefreshTokensDTO dto) {
		RefreshTokens refToken = refTokRepo.findByToken(dto.getToken())
				.orElseThrow(() -> new RuntimeException("Invalid refresh token"));

		if (refToken.getExpiresAt().isBefore(Instant.now())) {
			throw new RuntimeException("Refresh token expired");
		}

		User user = refToken.getUser();
		String newToken = JWTService.generateToken(Map.of("role", user.getRole().name()), user);

		return newToken;
	}

}
