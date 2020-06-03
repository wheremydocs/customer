package com.wheremydocs.customer.repository.specification;

import com.wheremydocs.customer.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class CustomerByUsernameSp implements Specification<Customer> {

  private final String username;

  @Override
  public Predicate toPredicate(Root<Customer> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
    if (username == null || username.isBlank()) {
      return null;
    }

    return cb.equal(cb.lower(r.get(Customer.Fields.username)), username.strip().toLowerCase());
  }
}
