package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    @Query(value = "select e from Customer e where e.name = ?1")
    Page<Customer> findAllByCustomerName(String name, Pageable pageable);

    @Query(value = "select e from Customer e where e.number = ?1")
    Page<Customer> findAllByCustomerNumber(String number, Pageable pageable);
}
