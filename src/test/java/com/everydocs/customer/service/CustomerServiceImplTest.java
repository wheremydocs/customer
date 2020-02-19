package com.everydocs.customer.service;

import com.everydocs.customer.domain.Customer;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerServiceImplTest {

  @Test
  void save_null_null() {
    // Given
    CustomerServiceImpl service = new CustomerServiceImpl(null);

    // When
    Customer customer = service.save(null);

    // Then
    assertNull(customer);
  }

  @Test
  void findById_null_empty() {
    // Given
    CustomerServiceImpl service = new CustomerServiceImpl(null);

    // When
    Optional<Customer> customer = service.findById(null);

    // Then
    assertTrue(customer.isEmpty());
  }
}