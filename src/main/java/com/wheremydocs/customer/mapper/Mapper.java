package com.wheremydocs.customer.mapper;

import java.util.Collection;
import java.util.List;

public interface Mapper<E, D> {

  E toEntity(D dto);

  E toEntity(D dto, E entity);

  D toDto(E entity);

  List<D> toDto(Collection<E> entityList);
}
