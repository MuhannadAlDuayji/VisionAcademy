package org.example.dao.registration;

import org.example.dao.AbstractDAO;
import org.example.exceptions.DAOException;
import org.example.model.Registration;

import java.util.List;

public abstract class RegistrationDAO extends AbstractDAO<Registration,Long> {

    public abstract List<Registration> findStudentById(Long id) throws DAOException;
    public abstract List<Registration> findCourseById(Long id) throws DAOException;
}
