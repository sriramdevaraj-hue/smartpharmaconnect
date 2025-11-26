package com.geekyants.pricingservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.pricingservice.dto.RulesDTO;
import com.geekyants.pricingservice.entity.PriceRules;
import com.geekyants.pricingservice.entity.Rules;
import com.geekyants.pricingservice.service.PriceService;

@RestController
@RequestMapping("/api/v1")
public class PriceController {

	@Autowired
	private PriceService priceServ;

	@PostMapping("/rules")
	public ResponseEntity<?> addPrice(@RequestBody RulesDTO rdto) {
		try {
			priceServ.insertPrice(rdto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Price Added Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Price Not Added !");
		}
	}

	@GetMapping("/rules")
	public ResponseEntity<?> viewPrice() {
		try {
			List<Rules> r = priceServ.getPrice();
			return ResponseEntity.status(HttpStatus.FOUND).body(r);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Price Not Viewed!");
		}
	}

	@GetMapping("/medicines/{id}/price")
	public ResponseEntity<?> latestPrice(@PathVariable("id") UUID id) {
		try {
			PriceRules pr = priceServ.updatedPrice(id);
			return ResponseEntity.status(HttpStatus.FOUND).body(pr);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error Latest Price Not Fetched....!");
		}
	}

}
