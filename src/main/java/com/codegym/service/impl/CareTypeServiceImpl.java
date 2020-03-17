package com.codegym.service.impl;

import com.codegym.model.CareType;
import com.codegym.repository.CareTypeRepository;
import com.codegym.service.CareTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CareTypeServiceImpl implements CareTypeService {
    @Autowired
    private CareTypeRepository careTypeRepository;

    @Override
    public Page<CareType> findAll(Pageable pageable) {
        return careTypeRepository.findAll(pageable);
    }

    @Override
    public CareType findById(Long id) {
        return careTypeRepository.findById(id).get();
    }

    @Override
    public void save(CareType careType) {
        careTypeRepository.save(careType);
    }

    @Override
    public void remove(Long id) {
        careTypeRepository.deleteById(id);
    }

    @Override
    public Page<CareType> findAllByCareTypeName(String name, Pageable pageable) {
        return careTypeRepository.findAllByCareTypeName(name, pageable);
    }
}
