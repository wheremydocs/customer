package com.wheremydocs.customer.rest.dto;

import com.wheremydocs.customer.domain.Customer;
import com.wheremydocs.customer.mapper.GenericMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper extends GenericMapper<Customer, CustomerDto> {

  public CustomerMapper() {
    super(Customer.class, CustomerDto.class);
  }

}
