package com.geekyants.inventoryservice.service;

import java.util.List;
import java.util.UUID;

import com.geekyants.inventoryservice.dto.MedicinesDTO;
import com.geekyants.inventoryservice.entity.Medicines;

public interface InventoryService {

    Medicines insertMedicines(MedicinesDTO dto);

    List<Medicines> listMedicines();

    Medicines getMedicinesById(UUID id);

}

