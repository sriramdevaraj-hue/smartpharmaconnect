package com.geekyants.pricingservice.service;

import java.util.List;
import java.util.UUID;

import com.geekyants.pricingservice.dto.RulesDTO;
import com.geekyants.pricingservice.entity.PriceRules;
import com.geekyants.pricingservice.entity.Rules;

public interface PriceService {

	Rules insertPrice(RulesDTO rdto);

	List<Rules> getPrice();

	PriceRules updatedPrice(UUID id);

}
