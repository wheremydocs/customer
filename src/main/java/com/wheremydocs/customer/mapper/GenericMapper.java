package com.wheremydocs.customer.mapper;

import com.wheremydocs.customer.domain.AuditableEntity;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public abstract class GenericMapper<E extends AuditableEntity, D> implements Mapper<E, D> {

  @Setter
  private ModelMapper mapper = new ModelMapper();

  private final Class<E> entityClass;
  private final Class<D> dtoClass;

  public GenericMapper(Class<E> entityClass, Class<D> dtoClass) {
    this.entityClass = entityClass;
    this.dtoClass = dtoClass;
  }

  protected Converter<E, D> toDtoConverter() {
    return context -> {
      E source = context.getSource();
      D destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  protected Converter<D, E> toEntityConverter() {
    return context -> {
      D source = context.getSource();
      E destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  public void mapSpecificFields(E source, D destination) {
  }

  public void mapSpecificFields(D source, E destination) {
  }

  @Override
  public E toEntity(D dto) {
    return Objects.isNull(dto) ? null : mapper.map(dto, entityClass);
  }

  @Override
  public E toEntity(D dto, E entity) {
    if (dto == null) {
      return null;
    } else {
      if (entity == null) {
        return mapper.map(dto, entityClass);
      } else {
        mapper.map(dto, entity);
        return entity;
      }
    }
  }

  @Override
  public D toDto(E entity) {
    return Objects.isNull(entity) ? null : mapper.map(entity, dtoClass);
  }

  @Override
  public List<D> toDto(Collection<E> entityList) {
    if (entityList == null) {
      return Collections.emptyList();
    }
    return entityList.stream()
        .map(this::toDto)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }
}
