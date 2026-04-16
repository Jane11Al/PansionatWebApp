package ru.ssau.service;

import java.util.List;

public interface BaseService<D, ID> {
    List<D> findAll();
    D findById(ID id);
    D create(D dto);
    D update(ID id, D dto);
    void delete(ID id);
}