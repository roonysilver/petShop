package com.codegym.repository;

import com.codegym.model.CareDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface CareDetailRepository extends PagingAndSortingRepository<CareDetail,Long> {
    @Query(value = "select e from CareDetail e where e.name = ?1")
    Page<CareDetail> findAllByCareDetailName(String name, Pageable pageable);

    @Query(value = "select e from CareDetail e where e.price = ?1")
    Page<CareDetail> findAllByCareDetailPrice(String price, Pageable pageable);

    @Query(value = "select e from CareDetail e where e.time = ?1")
    Page<CareDetail> findAllByCareDetailTime(String time, Pageable pageable);
}
