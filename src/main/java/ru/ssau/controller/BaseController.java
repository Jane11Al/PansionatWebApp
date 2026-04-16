package ru.ssau.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ssau.service.BaseService;

import java.util.List;

public abstract class BaseController<D, ID> {

    protected abstract BaseService<D, ID> getService();

    @GetMapping
    public List<D> getAll() {
        return getService().findAll();
    }

    @GetMapping("/{id}")
    public D getById(@PathVariable ID id) {
        return getService().findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public D create(@RequestBody D dto) {
        return getService().create(dto);
    }

    @PutMapping("/{id}")
    public D update(@PathVariable ID id, @RequestBody D dto) {
        return getService().update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable ID id) {
        getService().delete(id);
    }
}