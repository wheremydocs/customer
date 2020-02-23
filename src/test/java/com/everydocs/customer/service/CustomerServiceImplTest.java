package com.everydocs.customer.service;

import com.everydocs.customer.domain.Customer;
import com.everydocs.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

  @Test
  void save_null_null() {
    // Given
    CustomerServiceImpl service = new CustomerServiceImpl(null);

    // When
    Customer result = service.save(null);

    // Then
    assertNull(result);
  }

  @Test
  void save_customer_customer() {
    // Given
    Customer customer = new Customer();
    customer.setId(UUID.randomUUID());
    customer.setEmail("test@test.test");
    customer.setUsername("test");

    CustomerRepository repository = mock(CustomerRepository.class);
    when(repository.save(customer)).then(returnsFirstArg());
    CustomerServiceImpl service = new CustomerServiceImpl(repository);

    // When
    Customer result = service.save(customer);

    // Then
    assertEquals(customer, result);
  }

  @Test
  void findById_null_empty() {
    // Given
    CustomerServiceImpl service = new CustomerServiceImpl(null);

    // When
    Optional<Customer> result = service.findById(null);

    // Then
    assertTrue(result.isEmpty());
  }

  @Test
  void findById_existsId_customer() {
    // Given
    UUID id = UUID.randomUUID();
    Customer customer = new Customer();
    customer.setId(id);
    customer.setEmail("test@test.test");
    customer.setUsername("test");

    CustomerRepository repository = mock(CustomerRepository.class);
    when(repository.findById(id)).thenReturn(Optional.of(customer));
    CustomerServiceImpl service = new CustomerServiceImpl(repository);

    // When
    Optional<Customer> result = service.findById(id);

    // Then
    assertEquals(customer, result.orElse(null));
  }

  @Test
  void findById_notExistsId_empty() {
    // Given
    CustomerRepository repository = mock(CustomerRepository.class);
    when(repository.findById(any())).thenReturn(Optional.empty());

    CustomerServiceImpl service = new CustomerServiceImpl(repository);

    // When
    Optional<Customer> customer = service.findById(UUID.randomUUID());

    // Then
    assertTrue(customer.isEmpty());
  }

  @Test
  void find_nullSearch_notNull() {
    // Given
    CustomerRepository repository = mock(CustomerRepository.class);
    when(repository.findAll(Pageable.unpaged())).thenReturn(Page.empty());

    CustomerServiceImpl service = new CustomerServiceImpl(repository);

    // When
    Page<Customer> result = service.find(null, Pageable.unpaged());

    // Then
    assertTrue(result.isEmpty());
  }

  @Test
  void exists_null_searchAll() {
    // Given
    CustomerRepository repository = mock(CustomerRepository.class);
    when(repository.count()).thenReturn(1L);
    CustomerServiceImpl service = new CustomerServiceImpl(repository);

    // When
    boolean result = service.exists(null);

    // Then
    assertTrue(result);
  }

  @Test
  @SuppressWarnings("unchecked")
  void exists_notNull_withSpecification() {
    // Given
    CustomerSearch search = new CustomerSearch();
    search.setUsername("test123");

    CustomerRepository repository = mock(CustomerRepository.class);
    when(repository.count(any(Specification.class))).thenReturn(1L);
    CustomerServiceImpl service = new CustomerServiceImpl(repository);

    // When
    boolean result = service.exists(search);

    // Then
    assertTrue(result);
  }

  @Test
  void existsByUsername_null_false() {
    // Given
    CustomerServiceImpl service = new CustomerServiceImpl(null);

    // When
    boolean result = service.existsByUsername(null);

    // Then
    assertFalse(result);
  }

  @Test
  void existsByUsername_upperCase_caseInsensitive() {
    // Given
    String input = "UserName";
    CustomerRepository repository = mock(CustomerRepository.class);
    when(repository.existsByUsername("username")).thenReturn(true);
    CustomerServiceImpl service = new CustomerServiceImpl(repository);

    // When
    boolean result = service.existsByUsername(input);

    // Then
    assertTrue(result);
  }

  @Test
  void existsByUsername_withWhitespace_trim() {
    // Given
    String input = "    username1    ";
    CustomerRepository repository = mock(CustomerRepository.class);
    when(repository.existsByUsername("username1")).thenReturn(true);
    CustomerServiceImpl service = new CustomerServiceImpl(repository);

    // When
    boolean result = service.existsByUsername(input);

    // Then
    assertTrue(result);
  }

  @Test
  void existsByUsername_upperWithWhitespace_true() {
    // Given
    String input = "   \n UserName2  ";
    CustomerRepository repository = mock(CustomerRepository.class);
    when(repository.existsByUsername("username2")).thenReturn(true);
    CustomerServiceImpl service = new CustomerServiceImpl(repository);

    // When
    boolean result = service.existsByUsername(input);

    // Then
    assertTrue(result);
  }
}
