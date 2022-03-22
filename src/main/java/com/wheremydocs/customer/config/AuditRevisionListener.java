package com.wheremydocs.customer.config;

import com.wheremydocs.customer.domain.audit.AuditRevEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditRevisionListener implements RevisionListener {

  private static final String SYSTEM_USERNAME = "System Default";

  @Override
  public void newRevision(Object revisionEntity) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = null;
    if (auth != null) {
      name = auth.getName();
    }
    AuditRevEntity revEntity = (AuditRevEntity) revisionEntity;
    revEntity.setUsername(name == null ? SYSTEM_USERNAME : name);
  }
}
