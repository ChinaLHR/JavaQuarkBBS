package com.quark.common.Base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lhr on 17-8-1.
 */
public abstract class BaseServiceImpl<E extends JpaRepository,T> implements BaseService<T>{

    @Autowired
    protected E repository;

    @Override
    public T findOne(int key) {
        return (T) repository.findOne(key);
    }

    @Override
    public T save(T entity) {
        return (T) repository.save(entity);
    }

    @Override
    public void delete(Object key) {
        repository.delete(key);
    }

    @Override
    public List<T> findAll() {
       return repository.findAll();
    }
}
