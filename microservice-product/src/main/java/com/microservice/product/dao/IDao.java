package com.microservice.product.dao;

import java.util.List;

/**
 * Interface générique DAO pour les opérations CRUD de base.
 * @param <T> Type de l'entité
 */
public interface IDao<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(Long id);
    List<T> findAll();
}
