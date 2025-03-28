package com.tractive.pet.tracking.model;

import com.tractive.pet.tracking.common.model.PetType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("DOG")
@Data
public class Dog extends Pet {

	@Override
	public PetType getPetType() {
		return PetType.DOG;
	}
}
