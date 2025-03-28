package com.tractive.pet.tracking.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tractive.pet.tracking.common.model.PetType;
import com.tractive.pet.tracking.common.model.TrackerType;
import com.tractive.pet.tracking.dto.PetRequest;
import com.tractive.pet.tracking.model.Cat;
import com.tractive.pet.tracking.model.Pet;
import com.tractive.pet.tracking.serviceImpl.PetTrackingServiceImpl;

@ExtendWith(MockitoExtension.class)
class TrackingPetControllerTest {

	private MockMvc mockMvc;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Mock
	private PetTrackingServiceImpl petTrackingService;

	@InjectMocks
	private TrackingPetController trackingPetController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(trackingPetController).build();
	}

	@Test
	void testAddingPetIsSucess() throws Exception {

		PetRequest request = new PetRequest(PetType.CAT, TrackerType.SMALL, 123, false, false);
		Pet expectedPet = new Cat();
		expectedPet.setTrackerType(TrackerType.SMALL);
		expectedPet.setOwnerId(123);
		expectedPet.setInZone(false);
		((Cat) expectedPet).setLostTracker(false);

		when(petTrackingService.savePet(any(PetRequest.class))).thenReturn(expectedPet);

		mockMvc.perform(post("/api/tracking/add").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());

	}

	@Test
	void testGetListOfPetsOutSideTheZoneIsSuccess() throws Exception {

		Map<TrackerType, Long> catCounts = Map.of(TrackerType.SMALL, 2L, TrackerType.BIG, 1L);
		Map<TrackerType, Long> dogCounts = Map.of(TrackerType.SMALL, 3L, TrackerType.MEDIUM, 1L, TrackerType.BIG, 2L);
		Map<PetType, Map<TrackerType, Long>> expectedResponse = Map.of(PetType.CAT, catCounts, PetType.DOG, dogCounts);

		when(petTrackingService.getPetsCountOutsideZone()).thenReturn(expectedResponse);

		mockMvc.perform(get("/api/tracking/track/outside-zone")).andExpect(status().isOk());
	}
}
