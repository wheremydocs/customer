package com.wheremydocs.customer.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {

  @Column(name = "created", nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime created;

  @Column(name = "modified", nullable = false)
  @LastModifiedDate
  private LocalDateTime modified;
}
