package com.tractive.pet.tracking.serviceImpl;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tractive.pet.tracking.common.model.PetType;
import com.tractive.pet.tracking.common.model.TrackerType;
import com.tractive.pet.tracking.dto.PetRequest;
import com.tractive.pet.tracking.exception.InvalidInputException;
import com.tractive.pet.tracking.model.Cat;
import com.tractive.pet.tracking.model.Dog;
import com.tractive.pet.tracking.model.Pet;
import com.tractive.pet.tracking.repository.PetRepository;
import com.tractive.pet.tracking.service.PetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetTrackingServiceImpl implements PetService {

	private final PetRepository repository;

	public PetTrackingServiceImpl(PetRepository repository) {
		this.repository = repository;
	}

	@Override
	public Pet savePet(PetRequest saveRequest) throws Exception {

		if (saveRequest == null) {
			log.error("The request you have send here is null");
			throw new InvalidInputException("Request Is Null");
		} else {
			validateTrackerType(saveRequest);
			log.debug("Validation completed. Saving your pet to Database.");
			Pet savePet = convertToEntity(saveRequest);
			log.debug("Your pet is saved in database.");
			return repository.save(savePet);
		}
	}

	

	@Override
	public Map<PetType, Map<TrackerType, Long>> getPetsCountOutsideZone() {

		log.debug("Calling pets count outside zone from databse.");
		List<Object[]> results = repository.countPetsOutsideZoneByType();
		log.debug("Recieved list of pets outside zone from databse.");

		try {
			Map<PetType, Map<TrackerType, Long>> counts = new EnumMap<>(PetType.class);

			for (Object[] result : results) {
				PetType petType = (PetType) result[0];
				TrackerType trackerType = (TrackerType) result[1];
				Long count = (Long) result[2];

				counts.computeIfAbsent(petType, k -> new EnumMap<>(TrackerType.class)).put(trackerType, count);
			}
			return counts;

		} catch (Exception ex) {
			log.error("An Exeption Occured While Recieving Data from Database: " + ex.getMessage());
			throw ex;
		}
	}

	private void validateTrackerType(PetRequest request) throws Exception {
		
		if (request.getTrackerType() == null) {
			log.error("Tracker type cannot be null");
			throw new InvalidInputException("Tracker type cannot be null");
		} else {
			TrackerType trackerType = request.getTrackerType();
			if (request.getPetType() == PetType.CAT) {
				if (trackerType != TrackerType.SMALL && trackerType != TrackerType.BIG) {
					log.error("Invalid tracker type for Cat");
					throw new InvalidInputException(
							"Invalid tracker type for Cat. Allowed only: SMALL, BIG. But Received: " + trackerType);
				}
			} else if (request.getPetType() == PetType.DOG) {
				if (trackerType != TrackerType.SMALL && trackerType != TrackerType.MEDIUM
						&& trackerType != TrackerType.BIG) {
					throw new InvalidInputException(
							"Invalid tacker type for Dog. Allowed only: SMALL, MEDIUM, BIG. but Received: " + trackerType);
				}
			}
		}

	}
	
	
	private Pet convertToEntity(PetRequest petRequest) throws Exception {
		
		switch (petRequest.getPetType()) {
		case CAT:
			Cat cat = new Cat();
			cat.setLostTracker(petRequest.isLostTracker());
			setCommonPetProperties(cat, petRequest);
			return cat;
		case DOG:
			Dog dog = new Dog();
			setCommonPetProperties(dog, petRequest);
			return dog;
		default:
			throw new InvalidInputException("Uknown Pet Type: " + petRequest.getPetType());
		}
	}

	private void setCommonPetProperties(Pet pet, PetRequest request) {
		pet.setTrackerType(request.getTrackerType());
		pet.setOwnerId(request.getOwnerId());
		pet.setInZone(request.isInZone());
	}

}
