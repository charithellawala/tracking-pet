package com.tractive.pet.tracking.dto;

import com.tractive.pet.tracking.common.model.PetType;
import com.tractive.pet.tracking.common.model.TrackerType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {
	
	
	@Enumerated(EnumType.STRING)
    private PetType petType;
	private TrackerType trackerType;
    private Integer ownerId;
    private boolean inZone;
    private boolean lostTracker;

}
