package com.example.rsserver.common.service;

import com.example.rsserver.common.entity.AbstractEntity;
import com.example.rsserver.common.repository.CommonRepository;
import com.example.rsserver.utils.DomainObjectMerger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E>{

    private final R repository;

    private DomainObjectMerger domainObjectMerger;

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
    public E editPart(Long id, E entity) {
        Optional<E> optionalEntity = repository.findById(id);
        E loadedEntity = optionalEntity.orElseThrow(() ->
                new IllegalArgumentException("Entity was not found " + id));
        domainObjectMerger.merge(loadedEntity, entity);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Autowired
    public void setDomainObjectMerger(DomainObjectMerger domainObjectMerger) {
        this.domainObjectMerger = domainObjectMerger;
    }
}
