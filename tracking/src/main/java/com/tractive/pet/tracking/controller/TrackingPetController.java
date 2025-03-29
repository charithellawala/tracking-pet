package com.tractive.pet.tracking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tractive.pet.tracking.common.model.PetType;
import com.tractive.pet.tracking.common.model.TrackerType;
import com.tractive.pet.tracking.dto.PetRequest;
import com.tractive.pet.tracking.model.Pet;
import com.tractive.pet.tracking.serviceImpl.PetTrackingServiceImpl;

@RestController
@RequestMapping("/api/tracking")
public class TrackingPetController {

	@Autowired
	private PetTrackingServiceImpl trackingServie;

	@PostMapping("/add")
	public ResponseEntity<Pet> addingPet(@RequestBody PetRequest request) throws Exception {
		return ResponseEntity.ok(trackingServie.savePet(request));
	}
	
	@GetMapping("/getallpets")
	public ResponseEntity<List<Pet>> getallPets() {
		return ResponseEntity.ok(trackingServie.getAllListOfPets());
	}

	@GetMapping("/track/outside-zone")
	public ResponseEntity<Map<PetType, Map<TrackerType, Long>>> getListOfPetsOutSideTheZone() {
		return ResponseEntity.ok(trackingServie.getPetsCountOutsideZone());
	}

}
