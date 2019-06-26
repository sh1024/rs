package com.example.rsserver.common.controller;

import com.example.rsserver.common.entity.AbstractEntity;
import com.example.rsserver.common.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractController <E extends AbstractEntity, S extends CommonService<E>>
        implements CommonController<E> {

    private final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public E getSingle(long id) {
        return service.getSingle(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "The required entity not found"));
    }

    @Override
    public Iterable<E> getAll() {
        return service.getAll();
    }

    @Override
    public E create(E entity) {
        return service.create(entity);
    }

    @Override
    public E edit(long id, E entity) {
        return service.edit(id, entity);
    }

    @Override
    public E partialEdit(long id, E entity) {
        return service.partialEdit(id, entity);
    }

    @Override
    public void delete(long id) {
        service.delete(id);
    }
}
