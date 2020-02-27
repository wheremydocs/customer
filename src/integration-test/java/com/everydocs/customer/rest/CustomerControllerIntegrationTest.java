package com.everydocs.customer.rest;

import com.everydocs.customer.rest.dto.CustomerDto;
import com.everydocs.customer.support.AbstractIntegrationTest;
import com.everydocs.customer.support.TypeUtils;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerIntegrationTest extends AbstractIntegrationTest {

  @Test
  public void findCustomerById_incorrectId_badRequest() throws Exception {
    // Given
    String incorrectId = "Not UUID";

    // When
    MvcResult mvcResult = mvc.perform(get("/" + incorrectId)).andReturn();

    // Then
    int status = mvcResult.getResponse().getStatus();
    assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.resolve(status));
  }

  @Test
  public void findCustomerById_exists_found() throws Exception {
    // Given
    UUID existingId = UUID.fromString("61a983cc-d754-4dc2-8472-c9e3ae83d84d");

    // When
    MvcResult mvcResult = mvc.perform(get("/" + existingId)).andExpect(status().isOk()).andReturn();

    // Then
    CustomerDto customer =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), CustomerDto.class);

    assertEquals(existingId, customer.getId());
  }

  @Test
  public void findCustomerById_notExists_notFound() throws Exception {
    // Given
    UUID notExistingId = UUID.fromString("0000000-d754-4dc2-8472-c9e3ae83d84d");

    // When
    MvcResult mvcResult = mvc.perform(get("/" + notExistingId)).andReturn();

    // Then
    int status = mvcResult.getResponse().getStatus();
    assertEquals(HttpStatus.NOT_FOUND, HttpStatus.resolve(status));
  }

  @Test
  public void findCustomers_notEmptyDB_notZero() throws Exception {
    // Given
    // When
    MvcResult mvcResult = mvc.perform(get("/")).andExpect(status().isOk()).andReturn();

    // Then
    Page<CustomerDto> customers =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), TypeUtils.pageTypeRef());
    assertFalse(customers.isEmpty());
  }

  @Test
  public void findCustomers_moreThanLastPage_zeroPage() throws Exception {
    // Given
    // When
    MvcResult mvcResult =
        mvc.perform(get("/?size=99999&page=99999")).andExpect(status().isOk()).andReturn();

    // Then
    Page<CustomerDto> customers =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), TypeUtils.pageTypeRef());
    assertTrue(customers.isEmpty());
  }
}
