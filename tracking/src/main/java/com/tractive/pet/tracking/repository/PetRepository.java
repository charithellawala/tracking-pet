package com.tractive.pet.tracking.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tractive.pet.tracking.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	// @Query("SELECT p.petType, COUNT(p) FROM Pet p WHERE p.inZone = false GROUP BY
	// p.petType")
	@Query("SELECT p.petType, p.trackerType, COUNT(p) " + "FROM Pet p " + "WHERE p.inZone = false "
			+ "GROUP BY p.petType, p.trackerType")
	List<Object[]> countPetsOutsideZoneByType();
//	@Query("SELECT NEW map(p.petType as type, COUNT(p) as count) FROM Pet p WHERE p.inZone = false GROUP BY p.petType")
//    List<Map<String, Object>> countPetsOutsideZoneByType();

}
