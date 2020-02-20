package com.everydocs.customer;

import com.everydocs.customer.support.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ApplicationIntegrationTest extends AbstractIntegrationTest {

  @Test
  public void contextLoads() {
  }

  @Test
  public void healthy() throws Exception {
    //Given
    //When
    MvcResult mvcResult = mvc.perform(get("/actuator/health")).andReturn();

    //Then
    int status = mvcResult.getResponse().getStatus();
    assertEquals(HttpStatus.OK, HttpStatus.resolve(status));
  }
}
