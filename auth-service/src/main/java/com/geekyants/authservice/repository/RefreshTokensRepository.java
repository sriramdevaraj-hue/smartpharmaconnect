package com.geekyants.authservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekyants.authservice.entity.RefreshTokens;

@Repository
public interface RefreshTokensRepository extends JpaRepository<RefreshTokens, UUID> {
	
	Optional<RefreshTokens> findByToken(String token);

	void deleteByUserId(UUID userId);
}
