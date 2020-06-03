package com.wheremydocs.customer.service;

import com.wheremydocs.customer.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
  Optional<Customer> findById(UUID id);

  Page<Customer> find(CustomerSearch search, Pageable page);

  boolean exists(CustomerSearch search);

  boolean existsByUsername(String username);

  Customer save(Customer customer);
}
