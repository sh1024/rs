package com.example.rsserver.common.service;

import com.example.rsserver.common.entity.AbstractEntity;
import java.util.Optional;

public interface CommonService<E extends AbstractEntity> {

    Optional<E> getSingle(long id);

    Iterable<E> getAll();

    E create(E entity);

    E edit(Long id, E entity);

    E partialEdit(Long id, E entity);

    void delete(Long id);

}
