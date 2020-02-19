package com.everydocs.customer.repository.specification;

import com.everydocs.customer.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CustomerByQuerySp implements Specification<Customer> {

  private final String query;

  @Override
  public Predicate toPredicate(Root<Customer> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
    if (query == null || query.isBlank()) {
      return null;
    }

    List<Predicate> predicates = new ArrayList<>();

    String startWith = query + "%";

    predicates.add(cb.like(r.get(Customer.Fields.username), startWith));
    predicates.add(cb.like(r.get(Customer.Fields.email), startWith));

    return predicates.isEmpty() ? null : cb.or(predicates.toArray(Predicate[]::new));
  }
}
