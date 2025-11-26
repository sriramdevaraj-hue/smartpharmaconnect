package com.geekyants.inventoryservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekyants.common.events.MedicineUpdatedEvent;
import com.geekyants.inventoryservice.dto.MedicinesDTO;
import com.geekyants.inventoryservice.entity.Medicines;
import com.geekyants.inventoryservice.rabbitmqconfig.PublishConfig;
import com.geekyants.inventoryservice.repository.InventoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository invRepo;
    
    @Autowired
    private RabbitTemplate template;

    public Medicines insertMedicines(MedicinesDTO dto) {

        Medicines med = new Medicines();
        med.setName(dto.getName());
        med.setCategory(dto.getCategory());
        med.setStock(dto.getStock());
        med.setUpdatedAt(LocalDateTime.now());
        Medicines m = invRepo.save(med);
        
        MedicineUpdatedEvent events = new MedicineUpdatedEvent();
        events.setMedicineID(m.getId());
        events.setName(m.getName());
        template.convertAndSend(PublishConfig.EXCHANGE,PublishConfig.ROUTING_KEY,events);
        return m;
    }

    public List<Medicines> listMedicines() {
        return invRepo.findAll();
    }

    public Medicines getMedicinesById(UUID id) {
        Optional<Medicines> med = invRepo.findById(id);
        if (med.isPresent() && !med.isEmpty()) {
            return med.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
    
}

