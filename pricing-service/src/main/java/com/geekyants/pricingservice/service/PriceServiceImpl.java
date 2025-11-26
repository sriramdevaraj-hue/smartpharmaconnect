package com.geekyants.pricingservice.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekyants.common.events.MedicineUpdatedEvent;
import com.geekyants.common.events.PricingUpdatedEvent;
import com.geekyants.pricingservice.dto.RulesDTO;
import com.geekyants.pricingservice.entity.PriceRules;
import com.geekyants.pricingservice.entity.Rules;
import com.geekyants.pricingservice.rabbitmqconfig.PublishConfig;
import com.geekyants.pricingservice.repository.PriceRulesRepository;
import com.geekyants.pricingservice.repository.RulesRepository;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private RulesRepository rulesRepo;
	
	@Autowired
	private PriceRulesRepository priceRulesRepo;
	
	@Autowired
    private RabbitTemplate template;
	
	

	public Rules insertPrice(RulesDTO rdto) {
		Rules r = new Rules();
		r.setBasePrice(rdto.getBasePrice());
		r.setDiscountPercent(rdto.getDiscountPercent());
		r.setEffectiveFrom(new Date());
		return rulesRepo.save(r);
	}

	public List<Rules> getPrice() {
		return rulesRepo.findAll();
	}
	
	
	
	public void calculatePrice(MedicineUpdatedEvent events) {
		
		//ListrulesRepo
		//List<Rules> findAllRules = rulesRepo.findAll();
		
		// 1. Fetch the most recent rule applicable for the medicine's date
	    List<Rules> applicableRules = rulesRepo.findApplicableRule(new Date());

	    if (applicableRules.isEmpty()) {
	        System.out.println("No rule found for medicine date: " + new Date());
	        return;
	    }
		
	    
	    Rules rule = applicableRules.get(0); // latest applicable rule

	    // 2. Calculate final price
	    double basePrice = rule.getBasePrice();
	    double discount = rule.getDiscountPercent();
	    double finalPrice = basePrice * (1 - (discount / 100.0));
		//int final_price = base_price * (1 - discount_percent / 100);
		
		
		PriceRules pr = new PriceRules();
		pr.setFinalPrice(finalPrice);
		pr.setMedicineId((UUID) events.getMedicineID());
		pr.setCalculatedAt(Instant.now());
		priceRulesRepo.save(pr);
		
		PricingUpdatedEvent pev = new PricingUpdatedEvent();
		pev.setMedicineId(pr.getMedicineId());
		pev.setNewPrice(pr.getFinalPrice());
        template.convertAndSend(PublishConfig.EXCHANGE,PublishConfig.ROUTING_KEY,events);


	}

	public PriceRules updatedPrice(UUID id) {
		PriceRules price = priceRulesRepo.findByMedicineId(id).orElseThrow();
        return price;
	}
	



	


}
