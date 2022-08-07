package com.duykypaul.painthouse.service;

import com.duykypaul.painthouse.common.MapperUtils;
import com.duykypaul.painthouse.common.MessageUtils;
import com.duykypaul.painthouse.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
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
    public D findById(I id) {
        Optional<E> element = repository.findById(id);
        return element.map(this::toDTO).orElse(null);
    }

    @Override
    public List<D> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Page<D> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toDTO);
    }

    @Override
    public D saveOrUpdate(D element) {
        try {
            if (Objects.isNull(element)) throw new ApplicationException(MessageUtils.getMessage("message.notfound"));
            E entity = repository.save(toEntity(element));
            return toDTO(entity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Long updateIgnoreNull(D element, I id) {
        try {
            if (Objects.isNull(element)) return null;
            repository.findById(id).map(item -> {
                MapperUtils.transformToObject(element, item);
//                UpdateUtils.copyNullProperties(element, item);
                repository.save(item);
                return id;
            }).orElseThrow(() -> new ApplicationException(MessageUtils.getMessage("message.notfound", id)));
            return (Long) id;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return -1L;
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
    public Page<D> findByExample(Example<E> dExample, Pageable pageable) {
        return repository.findAll(dExample, pageable)
                .map(this::toDTO);
    }

    @Override
    public Optional<D> findOneByExample(Example<E> dExample) {
        return repository.findOne(dExample)
                .map(this::toDTO);
    }

}
