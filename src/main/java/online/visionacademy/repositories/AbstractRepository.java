package online.visionacademy.repositories;

import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.dao.Identifiable;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.exceptions.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends Identifiable,ID> implements GenericRepository<T,ID> {

    private AbstractDAO<T,ID> dao;

    public AbstractRepository(){
        dao = getAbstractDAO();
    }

    public abstract AbstractDAO getAbstractDAO();

    @Override
    public Integer count() throws PersistenceException {

        try {
            return dao.count();
        }catch (DAOException e){
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public boolean contains(ID id) throws PersistenceException {

        try {

            return dao.readById(id).isPresent();

        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }

    }

    @Override
    public T add(T entity) throws PersistenceException {

        T temp;

        try {
            temp = dao.insert(entity);
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
        return temp;
    }

    @Override
    public List<T> addAll(List<T> entities) throws PersistenceException {

        List<T> temp = new ArrayList<>();

        for (T entity:entities) {
            try {
                temp.add(dao.insert(entity));
            } catch (DAOException e) {
                throw new PersistenceException(e.getMessage(),e);
            }
        }

        return temp;
    }

    @Override
    public Optional<T> findById(ID id) throws PersistenceException {

        try {

           return dao.readById(id);

        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public List<T> findAll() throws PersistenceException {

        try {
           return dao.readAll();
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public List<T> findAllById(List<ID> ids) throws PersistenceException {

        try {
            return dao.readAllById(ids);
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public T update(T entity) throws PersistenceException {

        try {
            return dao.update(entity);
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }

    }

    @Override
    public void removeById(ID id) throws PersistenceException {

        try {

            boolean isExists = contains(id);

            if(isExists){
             dao.delete(id);

            }else {
                throw new PersistenceException("Could not find id : "+id);
            }
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }

        System.out.println("Item has been Remove ...");
    }

    @Override
    public void remove(T entity) throws PersistenceException {

        try {
            dao.delete((ID)entity.getId());
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public void removeAllById(List<ID> ids) throws PersistenceException {

        try {
            for (ID id:ids) {
                System.out.println(id);
                dao.delete(id);
            }
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public List<T> findByColumn(String column, String value) throws PersistenceException {

        List<T> entities = new ArrayList<>();

        try {
            entities = dao.readByColumn(column,value);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        return entities;
    }
}
