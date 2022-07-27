package online.visionacademy.dao;

import online.visionacademy.exceptions.DataSourceException;
import online.visionacademy.exceptions.DAOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T,ID> {

    //create
    public abstract T insert(T entity) throws DAOException, DataSourceException, SQLException;

    //read
    public abstract Optional<T> readById(ID id) throws DAOException;

    public abstract List<T> readAll() throws DAOException;

    public abstract List<T> readAllById(List<ID> ids) throws DAOException;

    //update
    public abstract T update(T entity) throws DAOException;

    //delete
    public abstract void delete(ID id) throws DAOException;


    public List<T> readByColumn(String column, String value)  throws DAOException;
    public default String ddf(){
        System.out.println("" +
                "");
        return "Do you love me ... do you do you ?";
    }
}
