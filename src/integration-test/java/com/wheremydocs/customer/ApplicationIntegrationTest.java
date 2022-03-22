package com.wheremydocs.customer;

import com.wheremydocs.customer.support.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ApplicationIntegrationTest extends BaseIntegrationTest {

  @Test
  public void healthy() throws Exception {
    //Given
    //When
    MvcResult mvcResult = mvc.perform(get("/actuator/health")).andReturn();

    //Then
    int status = mvcResult.getResponse().getStatus();

    assertThat(HttpStatus.resolve(status)).isEqualTo(HttpStatus.OK);
  }

}
