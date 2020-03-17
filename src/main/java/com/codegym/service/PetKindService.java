package com.codegym.service;

import com.codegym.model.MyTeam;
import com.codegym.model.PetKind;
import com.codegym.model.PetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PetKindService {

    Page<PetKind> findAll(Pageable pageable);

    PetKind findById(Long id);

    void save(PetKind petKind);

    void remove(Long id);

    Page<PetKind> findAllByPetKindName(String name, Pageable pageable);

    Page<PetKind> findAllByPetKindStatus(String status, Pageable pageable);
}
