package com.geekyants.inventoryservice.controller;

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

import com.geekyants.inventoryservice.dto.MedicinesDTO;
import com.geekyants.inventoryservice.entity.Medicines;
import com.geekyants.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    @Autowired
    private InventoryService invServ;

    @PostMapping("/medicines")
    public ResponseEntity<?> addMedicines(@RequestBody MedicinesDTO dto) {
        try {
            invServ.insertMedicines(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Medicines Added Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicines Not Added !");

        }
    }

    @GetMapping("/medicines")
    public ResponseEntity<?> getMedicines() {
        try {
            List<Medicines> m = invServ.listMedicines();
            return ResponseEntity.status(HttpStatus.FOUND).body(m);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Medicines Found");

        }
    }

    @GetMapping("/medicines/{id}")
    public ResponseEntity<?> getMedicinesDetails(@PathVariable("id") UUID id) {
        try {
            Medicines m = invServ.getMedicinesById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(m);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Medicines Found");

        }
    }

}

