package com.everydocs.customer.rest;

import com.everydocs.customer.rest.dto.CustomerDto;
import com.everydocs.customer.rest.dto.CustomerMapper;
import com.everydocs.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping
public class CustomerController {

  private final CustomerService service;
  private final CustomerMapper mapper;

  @Autowired
  public CustomerController(CustomerService service, CustomerMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDto> findCustomerById(@PathVariable UUID id) {
    return service
        .findById(id)
        .map(mapper::toDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
