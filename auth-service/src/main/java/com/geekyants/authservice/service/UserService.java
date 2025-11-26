package com.geekyants.authservice.service;

import com.geekyants.authservice.dto.LoginDTO;
import com.geekyants.authservice.dto.RefreshTokensDTO;
import com.geekyants.authservice.dto.UserDTO;
import com.geekyants.authservice.entity.User;

public interface UserService {

	User registerUser(UserDTO dto);

	String loginUser(LoginDTO loginDTO);

	String tokensRefresh(RefreshTokensDTO dto);
	//String tokensRefresh(String authorizationHeader,String email);

}
