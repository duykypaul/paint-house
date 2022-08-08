package com.duykypaul.painthouse.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * E : Entity Class
 * I : type of ID element
 * D : DTO POJO
 *
 * @author duyky
 */
public interface GenericService<E, I, D> {
    D findById(I id);

    List<D> findAll();

    Page<D> findAll(Pageable pageable);

    D saveOrUpdate(D element);

    Long updateIgnoreNull(D element, I id);

    boolean isExist(I id);

    E toEntity(D element);

    D toDTO(E element);

    List<D> findByExample(Example<E> dExample);

    Page<D> findByExample(Example<E> dExample, Pageable pageable);

    Optional<D> findOneByExample(Example<E> dExample);
}
