package online.visionacademy.dao.registration;

import online.visionacademy.model.Registration;
import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.exceptions.DAOException;

import java.util.List;

public abstract class RegistrationDAO extends AbstractDAO<Registration,Long> {

    public abstract List<Registration> findStudentById(Long id) throws DAOException;
    public abstract List<Registration> findCourseById(Long id) throws DAOException;
}
