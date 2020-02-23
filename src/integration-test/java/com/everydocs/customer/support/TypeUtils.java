package com.everydocs.customer.support;

import com.fasterxml.jackson.core.type.TypeReference;

public class TypeUtils {

  public static <T> TypeReference<T> typeRef() {
    return new TypeReference<>() {
    };
  }

  public static <T> TypeReference<RestResponsePage<T>> pageTypeRef() {
    return new TypeReference<>() {
    };
  }

}
