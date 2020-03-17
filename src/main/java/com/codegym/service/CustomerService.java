package com.codegym.service;

import com.codegym.model.CareType;
import com.codegym.model.Customer;
import com.codegym.model.PetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);

    Page<Customer> findAllByCustomerName(String name, Pageable pageable);

    Page<Customer> findAllByCustomerNumber(String number, Pageable pageable);
}
