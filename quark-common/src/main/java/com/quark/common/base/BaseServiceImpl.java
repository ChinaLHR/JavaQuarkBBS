package com.quark.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lhr on 17-8-1.
 */
public class BaseServiceImpl<E extends JpaRepository,T> implements BaseService<T>{

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

    @Override
    public void deleteInBatch(Iterable<T> iterable) {
        repository.deleteInBatch(iterable);
    }

    @Override
    public List<T> findAll(Iterable<Integer> iterable) {
        return repository.findAll(iterable);
    }

    @Override
    public List<T> save(Iterable<T> iterable) {
        return repository.save(iterable);
    }


}
