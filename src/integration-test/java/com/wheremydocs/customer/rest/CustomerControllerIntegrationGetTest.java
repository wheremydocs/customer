package com.wheremydocs.customer.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wheremydocs.customer.rest.dto.CustomerDto;
import com.wheremydocs.customer.support.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerIntegrationGetTest extends BaseIntegrationTest {

  @Test
  public void findCustomerById_incorrectId_badRequest() throws Exception {
    // Given
    String incorrectId = "Not UUID";

    // When
    MvcResult mvcResult = mvc.perform(get("/" + incorrectId)).andReturn();

    // Then
    int status = mvcResult.getResponse().getStatus();
    assertThat(HttpStatus.resolve(status)).isEqualTo(HttpStatus.BAD_REQUEST);
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

    assertThat(customer.getId()).isEqualTo(existingId);
  }

  @Test
  public void findCustomerById_notExists_notFound() throws Exception {
    // Given
    UUID notExistingId = UUID.fromString("0000000-d754-4dc2-8472-c9e3ae83d84d");

    // When
    MvcResult mvcResult = mvc.perform(get("/" + notExistingId)).andReturn();

    // Then
    int status = mvcResult.getResponse().getStatus();
    assertThat(HttpStatus.resolve(status)).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void findCustomers_notEmptyDB_notZero() throws Exception {
    // Given
    // When
    MvcResult mvcResult = mvc.perform(get("/")).andExpect(status().isOk()).andReturn();

    // Then
    Page<CustomerDto> customers =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
    assertThat(customers).isNotEmpty();
  }

  @Test
  public void findCustomers_moreThanLastPage_zeroPage() throws Exception {
    // Given
    String size = "99999";
    String page = "99999";

    // When
    MvcResult mvcResult =
        mvc.perform(get("/").param("size", size).param("page", page))
            .andExpect(status().isOk())
            .andReturn();

    // Then
    Page<CustomerDto> customers =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
    assertThat(customers).isEmpty();
  }

  @Test
  public void findCustomers_queryTest_notZero() throws Exception {
    // Given
    String query = "test";

    // When
    MvcResult mvcResult =
        mvc.perform(get("/").param("query", query)).andExpect(status().isOk()).andReturn();

    // Then
    Page<CustomerDto> customers =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
    assertThat(customers).isNotEmpty();
  }

  @Test
  public void findCustomers_blankUsername_ignoreSearch() throws Exception {
    // Given
    String username = "       ";

    // When
    MvcResult mvcResult =
        mvc.perform(get("/").param("username", username)).andExpect(status().isOk()).andReturn();

    // Then
    Page<CustomerDto> customers =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
    assertThat(customers).isNotEmpty();
  }

  @Test
  public void findCustomers_threeExistingIds_findThree() throws Exception {
    // Given
    String[] ids =
        new String[] {
            "61a983cc-d754-4dc2-8472-c9e3ae83d84d",
            "b901b9c4-cc5f-4160-a73a-03d40acd46eb",
            "1c6df312-d4e3-4721-b1a7-7410cc862ad7"
        };

    // When
    MvcResult mvcResult =
        mvc.perform(get("/").param("ids", ids)).andExpect(status().isOk()).andReturn();

    // Then
    Page<CustomerDto> customers =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
    assertThat(customers.getTotalElements()).isEqualTo(3);
  }

  @Test
  public void findCustomers_oneExistingIds_findOne() throws Exception {
    // Given
    String[] ids =
        new String[] {
            "61a983cc-d754-4dc2-8472-c9e3ae83d84d",
            "e01ed57b-64ac-4cf7-acae-eaab4be37047", //not exists
            "b88982af-dc2f-4378-94f5-e3770866bbb5"  //not exists
        };

    // When
    MvcResult mvcResult =
        mvc.perform(get("/").param("ids", ids)).andExpect(status().isOk()).andReturn();

    // Then
    Page<CustomerDto> customers =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
    assertThat(customers.getTotalElements()).isEqualTo(1);
    assertThat(customers).allMatch(dto -> dto.getId().toString().equals(ids[0]));
  }

  @Test
  public void findCustomers_specificUsername_findOne() throws Exception {
    // Given
    String username = "test1";

    // When
    MvcResult mvcResult =
        mvc.perform(get("/").param("username", username)).andExpect(status().isOk()).andReturn();

    // Then
    Page<CustomerDto> customers =
        mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
    assertThat(customers.getTotalElements()).isEqualTo(1);
  }
}
