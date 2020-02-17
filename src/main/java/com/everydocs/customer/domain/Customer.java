package com.everydocs.customer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Audited
public class Customer extends AuditableEntity {

  @Id
  @GeneratedValue
  private UUID id;
}
