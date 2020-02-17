package com.everydocs.customer.rest.dto;

import com.everydocs.customer.domain.Customer;
import com.everydocs.customer.mapper.GenericMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper extends GenericMapper<Customer, CustomerDto> {

  public CustomerMapper() {
    super(Customer.class, CustomerDto.class);
  }

}
