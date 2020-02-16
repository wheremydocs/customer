package com.everydocs.customer.rest;


import com.everydocs.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CustomerController {

  private final CustomerService service;

  @Autowired
  public CustomerController(CustomerService service) {
    this.service = service;
  }


}
