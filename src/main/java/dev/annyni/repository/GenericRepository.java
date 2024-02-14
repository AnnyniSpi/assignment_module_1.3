package dev.annyni.repository;

import java.util.List;
import java.util.Optional;
public interface GenericRepository<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T save(T t);

    void deleteById(ID id);

    T update(T t);
}
