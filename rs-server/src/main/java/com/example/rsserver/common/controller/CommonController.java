package com.example.rsserver.common.controller;

import static com.example.rsserver.utils.ApiConstants.DELETE_API;
import static com.example.rsserver.utils.ApiConstants.EDIT_API;
import static com.example.rsserver.utils.ApiConstants.GET_API;
import static com.example.rsserver.utils.ApiConstants.NEW_API;

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

    @GetMapping(GET_API)
    E getSingle(@PathVariable long id);

    @GetMapping
    Iterable<E> getAll();

    @PostMapping(NEW_API)
    @ResponseBody
    E create(@RequestBody E entity);

    @PutMapping(EDIT_API)
    @ResponseBody E edit(@PathVariable long id, @RequestBody E entity);

    @PatchMapping(EDIT_API)
    @ResponseBody E editPart(@PathVariable long id, @RequestBody E entity);

    @DeleteMapping(DELETE_API)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable long id);

}
