package com.wheremydocs.customer.domain;

import com.wheremydocs.customer.domain.type.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@Data
@Entity
@Audited
public class Customer extends AuditableEntity {

  @Id private UUID id;

  private String firstName;

  private String lastName;

  @Email @NotBlank private String email;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private LocalDate birthday;

  private boolean premium = false;
}
