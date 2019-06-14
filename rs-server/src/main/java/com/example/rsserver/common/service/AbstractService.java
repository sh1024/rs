package com.example.rsserver.common.service;

import com.example.rsserver.common.entity.AbstractEntity;
import com.example.rsserver.common.repository.CommonRepository;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E>{

    private final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> getSingle(long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<E> getAll() {
        return repository.findAll();
    }

    @Override
    public E create(E entity) {
        return repository.save(entity);
    }

    @Override
    public E edit(Long id, E entity) {
        return repository.save(entity);
    }

    @Override
    //FIXME patch method
    public E editPart(Long id, E entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
