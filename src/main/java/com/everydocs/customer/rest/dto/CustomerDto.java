package com.everydocs.customer.rest.dto;

import com.everydocs.customer.domain.type.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class CustomerDto {

  private UUID id;

  @NotBlank
  private String username;

  @Email
  private String email;

  private Gender gender;

}
