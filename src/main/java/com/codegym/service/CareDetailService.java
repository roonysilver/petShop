package com.codegym.service;

import com.codegym.model.CareDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface CareDetailService {
    Page<CareDetail> findAll(Pageable pageable);

    CareDetail findById(Long id);

    void save(CareDetail careDetail);

    void remove(Long id);

    Page<CareDetail> findAllByCareDetailName(String name, Pageable pageable);

    Page<CareDetail> findAllByCareDetailPrice(String name, Pageable pageable);

    Page<CareDetail> findAllByCareDetailTime(String name, Pageable pageable);
}
