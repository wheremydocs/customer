package com.wheremydocs.customer.repository;

import com.wheremydocs.customer.domain.Customer;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepositoryImplementation<Customer, UUID> {

  boolean existsByUsername(@NotBlank String username);

}
