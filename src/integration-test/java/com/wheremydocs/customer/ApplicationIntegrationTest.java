package com.wheremydocs.customer;

import com.wheremydocs.customer.support.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ApplicationIntegrationTest extends BaseIntegrationTest {

  @Test
  public void contextLoads() {
    // Check that context even loads
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

  @Test
  public void swagger() throws Exception {
    //Given
    //When
    MvcResult mvcResult = mvc.perform(get("/v2/api-docs")).andReturn();

    //Then
    int status = mvcResult.getResponse().getStatus();
    assertEquals(HttpStatus.OK, HttpStatus.resolve(status));
  }
}
