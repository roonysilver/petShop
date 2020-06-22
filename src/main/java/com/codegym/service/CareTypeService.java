package com.codegym.service;

import com.codegym.model.CareDetail;
import com.codegym.model.CareType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CareTypeService {
    Page<CareType> findAll(Pageable pageable);

    CareType findById(Long id);

    void save(CareType careType);

    void remove(Long id);

    Page<CareType> findAllByCareTypeName(String name, Pageable pageable);
}
