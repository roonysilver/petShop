package com.codegym.repository;

import com.codegym.model.CareType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface CareTypeRepository extends PagingAndSortingRepository<CareType,Long> {
    @Query(value = "select e from CareType e where e.name = ?1")
    Page<CareType> findAllByCareTypeName(String name, Pageable pageable);

    @Override
    @Query("select e from CareType e where e.softDelete=false")
    Page<CareType> findAll(Pageable pageable);

    //Soft delete.
    @Query("update CareType e set e.softDelete=true where e.id=?1")
    @Modifying
    void softDelete(Long id);
}
