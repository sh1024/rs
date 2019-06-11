package com.example.rsserver.common.controller;

import com.example.rsserver.common.entity.AbstractEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface CommonController <E extends AbstractEntity> {

    @GetMapping("/{id}")
    E getSingle(@PathVariable long id);

    @GetMapping
    Iterable<E> getAll();

    @PostMapping("/new")
    @ResponseBody
    E create(@RequestBody E entity);

    @PutMapping("edit/{id}")
    @ResponseBody E edit(@PathVariable long id, @RequestBody E entity);

    @PatchMapping("edit/{id}")
    @ResponseBody E editPart(@PathVariable long id, @RequestBody E entity);

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable long id);

}
