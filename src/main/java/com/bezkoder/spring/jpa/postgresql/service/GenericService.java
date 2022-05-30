package com.bezkoder.spring.jpa.postgresql.service;

import java.util.List;

/**
 * E : Entity Class
 * I : type of Id element
 * D : DTO POJO
 * @author duyky
 */
public interface GenericService<E, I, D> {
    D get(I id);
    List<D> getAll(Integer pageNumber, Integer size);
    D saveOrUpdate(D element);
    void delete(I id);
    boolean isExist(I id);
    E toEntity(D element);
    D toDTO(E element);
}
