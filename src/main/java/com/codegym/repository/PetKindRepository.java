package com.codegym.repository;

import com.codegym.model.PetKind;
import com.codegym.model.PetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface PetKindRepository extends PagingAndSortingRepository<PetKind,Long> {
    @Query(value = "select e from PetKind e where e.name = ?1")
    Page<PetKind> findAllByPetKindName(String name, Pageable pageable);

    @Query(value = "select e from PetKind e where e.status = ?1")
    Page<PetKind> findAllByPetKindStatus(String status, Pageable pageable);
}
