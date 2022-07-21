package online.visionacademy.repositories.course;

import online.visionacademy.exceptions.PersistenceException;
import online.visionacademy.model.Course;
import online.visionacademy.repositories.AbstractRepository;

import java.util.List;

public abstract class CourseRepository extends AbstractRepository<Course,Long> {

    public abstract List<Course> findByStudentId(Long studentId) throws PersistenceException;
    public abstract void removeAllRegistration(Long courseId) throws PersistenceException;


    // student
    public abstract Integer studentCount(Long courseId) throws PersistenceException;
    public abstract boolean isRegistered(Long courseId, Long studentId) throws PersistenceException;
    public abstract void register(Long courseId, Long studentId) throws PersistenceException;
    public abstract void deRegistered(Long courseId, Long studentId) throws PersistenceException;


}
