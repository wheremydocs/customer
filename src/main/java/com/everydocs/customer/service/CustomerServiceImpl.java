package com.everydocs.customer.service;

import com.everydocs.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository rep;

  @Autowired
  public CustomerServiceImpl(CustomerRepository rep) {
    this.rep = rep;
  }
}
