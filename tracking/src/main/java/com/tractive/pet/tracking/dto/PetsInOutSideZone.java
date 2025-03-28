package com.tractive.pet.tracking.dto;

import com.tractive.pet.tracking.common.model.PetType;
import com.tractive.pet.tracking.common.model.TrackerType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetsInOutSideZone {
	
	private PetType petType;
	private TrackerType trackerType;
	private Long count;
}
