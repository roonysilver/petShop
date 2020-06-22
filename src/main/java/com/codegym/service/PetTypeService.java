package com.codegym.service;

import com.codegym.model.PetKind;
import com.codegym.model.PetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PetTypeService {
    Page<PetType> findAll(Pageable pageable);

    PetType findById(Long id);

    void save(PetType petType);

    void remove(Long id);

    Page<PetType> findAllByPetTypeName(String name, Pageable pageable);
}
