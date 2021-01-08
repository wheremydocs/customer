package com.wheremydocs.customer.config;

import com.wheremydocs.customer.domain.audit.AuditRevEntity;
import com.wheremydocs.customer.repository.CustomerRepository;
import com.wheremydocs.customer.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuditRevisionListenerTest {

  @Test
  void existsByUsername_upperWithWhitespace_true() {
    // Given
    AuditRevisionListener listener = new AuditRevisionListener();
    AuditRevEntity entity = new AuditRevEntity();

    // When
    listener.newRevision(entity);

    // Then
    assertFalse(entity.getUsername().strip().isBlank());
  }
}
