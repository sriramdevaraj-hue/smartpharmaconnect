package com.geekyants.common.events;

import java.util.UUID;

import lombok.Data;

@Data
public class MedicineUpdatedEvent {
	
	private UUID medicineID;
	private String name;
	
	public UUID getMedicineID() {
		return medicineID;
	}
	public void setMedicineID(UUID medicineID) {
		this.medicineID = medicineID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
