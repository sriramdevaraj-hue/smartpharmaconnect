package com.geekyants.pricingservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.geekyants.pricingservice.entity.Rules;

@Repository
public interface RulesRepository extends JpaRepository<Rules, Long> {
	
	@Query("SELECT r FROM Rules r WHERE r.effectiveFrom <= :medicineDate ORDER BY r.effectiveFrom DESC")
	List<Rules> findApplicableRule(@Param("medicineDate") Date medicineDate);

}
