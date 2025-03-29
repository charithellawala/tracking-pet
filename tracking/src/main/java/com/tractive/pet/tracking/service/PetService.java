package com.tractive.pet.tracking.service;

import java.util.List;
import java.util.Map;

import com.tractive.pet.tracking.common.model.PetType;
import com.tractive.pet.tracking.common.model.TrackerType;
import com.tractive.pet.tracking.dto.PetRequest;
import com.tractive.pet.tracking.model.Pet;

public interface PetService {

	Pet savePet(PetRequest pet) throws Exception;
	Map<PetType, Map<TrackerType, Long>> getPetsCountOutsideZone();
	List<Pet> getAllListOfPets();
		
}
