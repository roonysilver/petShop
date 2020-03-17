package com.codegym.service.impl;

import com.codegym.model.CareDetail;
import com.codegym.repository.CareDetailRepository;
import com.codegym.service.CareDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CareDetailServiceImpl implements CareDetailService {
    @Autowired
    private CareDetailRepository careDetailRepository;

    @Override
    public Page<CareDetail> findAll(Pageable pageable) {
        return careDetailRepository.findAll(pageable);
    }

    @Override
    public CareDetail findById(Long id) {
        return careDetailRepository.findById(id).get();
    }

    @Override
    public void save(CareDetail careDetail) {
        careDetailRepository.save(careDetail);
    }

    @Override
    public void remove(Long id) {
        careDetailRepository.deleteById(id);
    }

    @Override
    public Page<CareDetail> findAllByCareDetailName(String name, Pageable pageable) {
        return careDetailRepository.findAllByCareDetailName(name, pageable);
    }

    @Override
    public Page<CareDetail> findAllByCareDetailPrice(String price, Pageable pageable) {
        return careDetailRepository.findAllByCareDetailPrice(price, pageable);
    }

    @Override
    public Page<CareDetail> findAllByCareDetailTime(String time, Pageable pageable) {
        return careDetailRepository.findAllByCareDetailTime(time, pageable);
    }
}
