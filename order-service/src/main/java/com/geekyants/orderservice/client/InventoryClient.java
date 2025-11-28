package com.geekyants.orderservice.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.geekyants.orderservice.dto.InventoryResponseDTO;

@FeignClient(name="inventory-service",path="/api/v1")
public interface InventoryClient {
	
	@GetMapping("/medicines/{id}")
	InventoryResponseDTO getMedicinesDetails(@PathVariable("id") UUID id);

}
