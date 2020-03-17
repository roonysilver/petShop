package com.codegym.service.impl;

import com.codegym.model.Pet;
import com.codegym.repository.PetRepository;
import com.codegym.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepository petRepository;

    @Override
    public Page<Pet> findAll(Pageable pageable) {
        return petRepository.findAll(pageable);
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).get();
    }

    @Override
    public void save(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    public void remove(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public Page<Pet> findAllByPetName(String name, Pageable pageable) {
        return petRepository.findAllByPetName(name, pageable);
    }

    @Override
    public Page<Pet> findAllByPetAge(String age, Pageable pageable) {
        return petRepository.findAllByPetAge(age, pageable);
    }

    @Override
    public Page<Pet> findAllByPetWeight(String weight, Pageable pageable) {
        return petRepository.findAllByPetWeight(weight, pageable);
    }

    @Override
    public Page<Pet> findAllByPetColor(String color, Pageable pageable) {
        return petRepository.findAllByPetColor(color, pageable);
    }
}
