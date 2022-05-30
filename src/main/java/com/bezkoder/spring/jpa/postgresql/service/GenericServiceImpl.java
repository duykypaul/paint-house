package com.bezkoder.spring.jpa.postgresql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public abstract class GenericServiceImpl<E, I, D> implements GenericService<E, I, D> {

    protected JpaRepository<E, I> repository;

    protected GenericServiceImpl(JpaRepository<E, I> repository) {
        this.repository = repository;
    }

    @Override
    public D find(I id) {
        Optional<E> element = repository.findById(id);
        return element.map(this::toDTO).orElse(null);
    }

    @Override
    public Page<D> getAll(Integer pageNumber, Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return repository.findAll(pageable).map(this::toDTO);
    }

    @Override
    public D saveOrUpdate(D element) {
        try {
            if (Objects.isNull(element)) return null;
            E entity = repository.save(toEntity(element));
            return toDTO(entity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean isExist(I id) {
        return repository.existsById(id);
    }

    @Override
    public List<D> findByExample(Example<E> dExample) {
        return repository.findAll(dExample)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public D findOneByExample(Example<E> dExample) {
        return repository.findOne(dExample)
                .map(this::toDTO)
                .orElse(null);
    }

}
