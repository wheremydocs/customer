package com.wheremydocs.customer.domain;

import com.wheremydocs.customer.domain.type.Gender;
import com.wheremydocs.customer.domain.type.PostgresSQLEnumType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
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
@TypeDef(name = "pgsql_enum", typeClass = PostgresSQLEnumType.class)
public class Customer extends AuditableEntity {

  @Id
  @GeneratedValue
  private UUID id;

  @NotBlank
  private String username;

  @Email
  private String email;

  @Type(type = "pgsql_enum")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  private LocalDate birthday;

}
