package com.everydocs.customer.service;

import com.everydocs.customer.domain.Customer;
import com.everydocs.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository rep;

  @Autowired
  public CustomerServiceImpl(CustomerRepository rep) {
    this.rep = rep;
  }

  @Override
  public Optional<Customer> findById(UUID id) {
    if (id == null) {
      return Optional.empty();
    }
    return rep.findById(id);
  }

  @Override
  public Page<Customer> find(CustomerSearch search, Pageable page) {
    // TODO use specification pattern
    return Page.empty();
  }

  @Override
  public Customer save(Customer customer) {
    if (customer == null) {
      return null;
    }
    return save(customer);
  }
}
