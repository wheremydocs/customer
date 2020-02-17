package com.everydocs.customer.service;

import com.everydocs.customer.domain.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
  Optional<Customer> findById(UUID id);
}
