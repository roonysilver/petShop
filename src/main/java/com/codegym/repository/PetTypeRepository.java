package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.Pet;
import com.codegym.model.PetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface PetTypeRepository extends PagingAndSortingRepository<PetType,Long> {
    @Query(value = "select e from PetType e where e.name = ?1")
    Page<PetType> findAllByPetTypeName(String name, Pageable pageable);

    @Override
    @Query("select e from PetType e where e.softDelete=false")
    Page<PetType> findAll(Pageable pageable);

    //Soft delete.
    @Query("update PetType e set e.softDelete=true where e.id=?1")
    @Modifying
    void softDelete(Long id);
}
