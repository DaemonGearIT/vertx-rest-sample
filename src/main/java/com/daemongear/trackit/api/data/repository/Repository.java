package com.daemongear.trackit.api.data.repository;

import com.daemongear.trackit.api.data.entity.Entity;

import java.util.List;

/**
 * Created by robertoosorio on 15-08-16.
 */
public interface Repository<E extends Entity, PK> {
    E findOne(PK id);
    List<E> findAll();
    PK save(E entity);
    PK create(E entity);
    PK update(E entity);
    void delete(PK id, boolean soft);
}
