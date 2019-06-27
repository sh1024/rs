package com.example.rsserver.common.service;

import com.example.rsserver.common.entity.AbstractEntity;
import com.example.rsserver.common.repository.CommonRepository;
import com.example.rsserver.utils.DomainObjectMerger;
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
        E loadedEntity = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Entity was not found " + id));
        entity.setId(id);
        // I know what I'm doing
        entity.setVersion(loadedEntity.getVersion());
        entity.setCreatedAt(loadedEntity.getCreatedAt());
        return repository.save(entity);
    }

    @Override
    public E partialEdit(Long id, E entity) {
        Optional<E> optionalEntity = repository.findById(id);
        E loadedEntity = optionalEntity.orElseThrow(() ->
                new IllegalArgumentException("Entity was not found " + id));
        DomainObjectMerger.merge(loadedEntity, entity);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
