package com.everydocs.customer.domain.audit;

import com.everydocs.customer.config.AuditRevisionListener;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.*;

@Entity
@RevisionEntity(AuditRevisionListener.class)
@Table(name = "REVINFO", schema = "audit")
@AttributeOverrides({
    @AttributeOverride(name = "timestamp", column = @Column(name = "REVTSTMP")),
    @AttributeOverride(name = "id", column = @Column(name = "REV"))
})
@Getter
@Setter
public class AuditRevEntity extends DefaultRevisionEntity {

  private String username;
}

