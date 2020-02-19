package com.everydocs.customer.repository;

import com.everydocs.customer.domain.Customer;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepositoryImplementation<Customer, UUID> {
}
