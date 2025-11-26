package com.geekyants.inventoryservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekyants.inventoryservice.entity.Medicines;

public interface InventoryRepository extends JpaRepository <Medicines,UUID> {

}

