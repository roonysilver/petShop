package com.codegym.service;

import com.codegym.model.CareDetail;
import com.codegym.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PetService {
    Page<Pet> findAll(Pageable pageable);

    Pet findById(Long id);

    void save(Pet pet);

    void remove(Long id);

    Page<Pet> findAllByPetName(String name, Pageable pageable);

    Page<Pet> findAllByPetAge(String age, Pageable pageable);

    Page<Pet> findAllByPetWeight(String weight, Pageable pageable);

    Page<Pet> findAllByPetColor(String color, Pageable pageable);
}
