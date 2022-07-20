package online.visionacademy.repositories;

import online.visionacademy.exceptions.PersistenceException;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T,ID> {

    public abstract Integer count() throws PersistenceException;
    public abstract boolean contains(ID id) throws PersistenceException;
    public abstract T add(T entity) throws PersistenceException;
    public abstract List<T> addAll(List<T> entities) throws PersistenceException;

    public abstract Optional<T> findById(ID id) throws PersistenceException;
    public abstract List<T> findAll() throws PersistenceException;
    public abstract List<T> findAllById(List<ID> ids) throws PersistenceException;

    public abstract T update(T entity) throws PersistenceException;
    public abstract void removeById(ID id) throws PersistenceException;
    public abstract void remove(T entity) throws PersistenceException;
    public abstract void removeAllById(List<ID> ids) throws PersistenceException;

    public abstract List<T> findByColumn(String column, String value) throws PersistenceException;

}
