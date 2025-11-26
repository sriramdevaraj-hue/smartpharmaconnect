package com.geekyants.pricingservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekyants.pricingservice.entity.PriceRules;

@Repository
public interface PriceRulesRepository extends JpaRepository<PriceRules, Long> {

	Optional<PriceRules> findByMedicineId(UUID medicineId);
	
}
