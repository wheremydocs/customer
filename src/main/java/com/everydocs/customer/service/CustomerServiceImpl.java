package com.everydocs.customer.service;

import com.everydocs.customer.domain.Customer;
import com.everydocs.customer.repository.CustomerRepository;
import com.everydocs.customer.repository.specification.CustomerByIdsSp;
import com.everydocs.customer.repository.specification.CustomerByQuerySp;
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
    if (search == null) {
      return rep.findAll(page);
    }
    CustomerByIdsSp customerByIdsSp = new CustomerByIdsSp(search.getIds());
    CustomerByQuerySp customerByQuerySp = new CustomerByQuerySp(search.getQuery());
    return rep.findAll(customerByIdsSp.and(customerByQuerySp), page);
  }

  @Override
  public Customer save(Customer customer) {
    if (customer == null) {
      return null;
    }
    return save(customer);
  }
}
