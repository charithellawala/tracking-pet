package com.tractive.pet.tracking.model;

import com.tractive.pet.tracking.common.model.PetType;
import com.tractive.pet.tracking.common.model.TrackerType;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pet_type", discriminatorType = DiscriminatorType.STRING)
@Data
public abstract class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "pet_type", insertable = false, updatable = false)
    private PetType petType;
	
	//private String trackerType;
	@Enumerated(EnumType.STRING)
    private TrackerType trackerType;
	private int ownerId;
	private boolean inZone;

}
