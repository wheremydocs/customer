package com.wheremydocs.customer.service;

import com.wheremydocs.customer.domain.Customer;
import com.wheremydocs.customer.repository.CustomerRepository;
import com.wheremydocs.customer.repository.specification.CustomerByIdsSp;
import com.wheremydocs.customer.repository.specification.CustomerByQuerySp;
import com.wheremydocs.customer.repository.specification.CustomerByUsernameSp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
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

    return rep.findAll(toSpecification(search), page);
  }

  private Specification<Customer> toSpecification(@NotNull CustomerSearch search) {
    CustomerByIdsSp customerByIdsSp = new CustomerByIdsSp(search.getIds());
    CustomerByQuerySp customerByQuerySp = new CustomerByQuerySp(search.getQuery());
    CustomerByUsernameSp customerByUsernameSp = new CustomerByUsernameSp(search.getUsername());
    Specification<Customer> combined = customerByIdsSp.and(customerByQuerySp);
    combined = combined.and(customerByUsernameSp);
    return combined;
  }

  @Override
  public boolean exists(CustomerSearch search) {
    if (search == null) {
      return rep.count() > 0;
    }

    return rep.count(toSpecification(search)) > 0;
  }

  @Override
  public boolean existsByUsername(String username) {
    if (username == null || username.isBlank()) {
      return false;
    }
    return rep.existsByUsername(username.strip().toLowerCase());
  }

  @Override
  public Customer save(Customer customer) {
    if (customer == null) {
      return null;
    }
    return rep.save(customer);
  }
}
