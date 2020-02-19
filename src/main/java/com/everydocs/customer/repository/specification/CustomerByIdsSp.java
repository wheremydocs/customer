package com.everydocs.customer.repository.specification;

import com.everydocs.customer.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
public class CustomerByIdsSp implements Specification<Customer> {

  private final Collection<UUID> ids;

  @Override
  public Predicate toPredicate(
      Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    if (ids == null) {
      return null;
    }
    return root.get(Customer.Fields.id).in(ids);
  }
}
