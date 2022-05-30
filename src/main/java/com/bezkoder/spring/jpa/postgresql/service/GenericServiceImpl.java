package com.bezkoder.spring.jpa.postgresql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class GenericServiceImpl<E, I, D> implements GenericService<E, I, D> {

    protected JpaRepository<E, I> repository;
    public GenericServiceImpl(JpaRepository<E, I> repository) {
        this.repository = repository;
    }

    @Override
    public D get(I id) {
        Optional<E> element = repository.findById(id);
        return element.map(this::toDTO).orElse(null);
    }

    @Override
    public List<D> getAll(Integer pageNumber, Integer size) {
        return null;
    }

    @Override
    public D saveOrUpdate(D element) {
        return null;
    }

    @Override
    public void delete(I id) {

    }

    @Override
    public boolean isExist(I id) {
        return false;
    }
}
