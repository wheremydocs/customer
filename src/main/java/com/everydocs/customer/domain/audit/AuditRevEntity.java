package com.everydocs.customer.domain.audit;

import com.everydocs.customer.config.AuditRevisionListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@RevisionEntity(AuditRevisionListener.class)
@Table(name = "REVINFO", schema = "audit")
@AttributeOverride(name = "timestamp", column = @Column(name = "REVTSTMP"))
@AttributeOverride(name = "id", column = @Column(name = "REV"))
@Data
@EqualsAndHashCode(callSuper = true)
public class AuditRevEntity extends DefaultRevisionEntity {

  private String username;
}

