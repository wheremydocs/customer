package com.everydocs.customer.config;

import com.everydocs.customer.domain.audit.AuditRevEntity;
import org.hibernate.envers.RevisionListener;

public class AuditRevisionListener implements RevisionListener {

  private static final String SYSTEM_USERNAME = "System Default";

  @Override
  public void newRevision(Object revisionEntity) {
    /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = null;
    if (auth != null) {
      name = auth.getName();
    }*/
    AuditRevEntity revEntity = (AuditRevEntity) revisionEntity;
    revEntity.setUsername(/*name == null ?*/ SYSTEM_USERNAME /*: name*/);
  }
}
