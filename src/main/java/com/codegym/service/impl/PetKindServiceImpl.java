package com.codegym.service.impl;

import com.codegym.model.PetKind;
import com.codegym.model.PetType;
import com.codegym.repository.PetKindRepository;
import com.codegym.service.PetKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PetKindServiceImpl implements PetKindService {
    @Autowired
    private PetKindRepository petKindRepository;

    @Override
    public Page<PetKind> findAll(Pageable pageable) {
        return petKindRepository.findAll(pageable);
    }

    @Override
    public PetKind findById(Long id) {
        return petKindRepository.findById(id).get();
    }

    @Override
    public void save(PetKind petKind) {
        petKindRepository.save(petKind);
    }

    @Override
    public void remove(Long id) {
        petKindRepository.deleteById(id);
    }

    @Override
    public Page<PetKind> findAllByPetKindName(String name, Pageable pageable) {
        return petKindRepository.findAllByPetKindName(name, pageable);
    }

    @Override
    public Page<PetKind> findAllByPetKindStatus(String status, Pageable pageable) {
        return petKindRepository.findAllByPetKindStatus(status, pageable);
    }
}
