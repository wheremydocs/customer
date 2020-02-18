package com.everydocs.customer.service;

import com.everydocs.customer.domain.type.Gender;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

@Data
public class CustomerSearch {

  @Size(max = 1000)
  private Collection<UUID> ids;

  private Gender gender;

  private Boolean blocked;

  private String query;

}
