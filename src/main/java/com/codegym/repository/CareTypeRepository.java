package com.codegym.repository;

import com.codegym.model.CareType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface CareTypeRepository extends PagingAndSortingRepository<CareType,Long> {
    @Query(value = "select e from CareType e where e.name = ?1")
    Page<CareType> findAllByCareTypeName(String name, Pageable pageable);
}
