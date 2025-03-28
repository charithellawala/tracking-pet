package com.tractive.pet.tracking.model;

import com.tractive.pet.tracking.common.model.PetType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("CAT")
@Data
public class Cat extends Pet{
	private boolean lostTracker;
	
	@Override
    public PetType getPetType() {
        return PetType.CAT;
    }
}
