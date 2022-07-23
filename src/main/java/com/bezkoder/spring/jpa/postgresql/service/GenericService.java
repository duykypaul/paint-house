package com.bezkoder.spring.jpa.postgresql.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * E : Entity Class
 * I : type of ID element
 * D : DTO POJO
 * @author duyky
 */
public interface GenericService<E, I, D> {
    D find(I id);
    Page<D> getAll(Integer pageNumber, Integer size);
    D saveOrUpdate(D element);
    boolean isExist(I id);
    E toEntity(D element);
    D toDTO(E element);

    List<D> findByExample(Example<E> dExample);

    D findOneByExample(Example<E> dExample);
}
