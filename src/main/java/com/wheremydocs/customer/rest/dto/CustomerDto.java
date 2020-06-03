package com.wheremydocs.customer.rest.dto;

import com.wheremydocs.customer.domain.type.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CustomerDto {

  private UUID id;

  @NotBlank
  private String username;

  @Email
  private String email;

  private Gender gender;

  private LocalDate birthday;

}
