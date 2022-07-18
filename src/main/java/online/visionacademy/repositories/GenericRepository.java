package online.visionacademy.repositories;

import online.visionacademy.exceptions.PersistentException;

import javax.swing.event.ListDataEvent;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<T,ID> {

    public abstract Integer count() throws PersistentException;
    public abstract boolean contains(ID id) throws PersistentException;
    public abstract T add(T entity) throws PersistentException;
    public abstract List<T> addAll(List<T> entities) throws PersistentException;

    public abstract Optional<T> findById(ID id) throws PersistentException;
    public abstract List<T> findAll() throws PersistentException;
    public abstract List<T> findAllById(List<ID> ids) throws PersistentException;

    public abstract T update(T entity) throws PersistentException;
    public abstract void removeById(ID id) throws PersistentException;
    public abstract void remove(T entity) throws PersistentException;
    public abstract void removeAllById(List<ID> ids) throws PersistentException;
}
