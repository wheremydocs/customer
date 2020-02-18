package com.everydocs.customer.rest;

import com.everydocs.customer.domain.Customer;
import com.everydocs.customer.rest.dto.CustomerDto;
import com.everydocs.customer.rest.dto.CustomerMapper;
import com.everydocs.customer.service.CustomerSearch;
import com.everydocs.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

  @GetMapping
  public Page<CustomerDto> findCustomers(@Valid CustomerSearch search, Pageable page) {
    return service.find(search, page).map(mapper::toDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDto> updateCustomer(@PathVariable UUID id, @RequestBody CustomerDto dto) {
    Customer customer = service.findById(id).orElse(null);
    if (customer == null) {
      return ResponseEntity.notFound().build();
    }

    //prevent using wrong id from body
    dto.setId(id);

    customer = service.save(mapper.toEntity(dto, customer));
    return ResponseEntity.ok(mapper.toDto(customer));
  }

}
