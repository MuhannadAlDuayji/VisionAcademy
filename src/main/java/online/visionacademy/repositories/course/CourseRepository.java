package online.visionacademy.repositories.course;

import online.visionacademy.exceptions.PersistentException;
import online.visionacademy.model.Course;
import online.visionacademy.repositories.AbstractRepository;

import java.util.List;

public abstract class CourseRepository extends AbstractRepository<Course,Long> {

    public abstract List<Course> findStudentId(Long studentId) throws PersistentException;
    public abstract void removeAllRegistration(Long courseId) throws PersistentException;


    // student
    public abstract Integer studentCount(Long courseId) throws PersistentException;
    public abstract boolean isRegistered(Long courseId, Long studentId) throws PersistentException;
    public abstract void register(Long courseId, Long studentId) throws PersistentException;
    public abstract void deRegistered(Long courseId, Long studentId) throws PersistentException;


}
