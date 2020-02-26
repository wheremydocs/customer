package com.everydocs.customer.rest;

import com.everydocs.customer.support.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class CustomerControllerIntegrationTest extends AbstractIntegrationTest {

  @Test
  public void findCustomerById_incorrectId_notFound() throws Exception {
    // Given
    String incorrectId = "Not UUID";

    // When
    MvcResult mvcResult = mvc.perform(get("/" + incorrectId)).andReturn();

    // Then
    int status = mvcResult.getResponse().getStatus();
    assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.resolve(status));
  }
}
