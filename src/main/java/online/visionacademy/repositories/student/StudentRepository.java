package online.visionacademy.repositories.student;

import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.exceptions.PersistentException;
import online.visionacademy.model.Student;
import online.visionacademy.repositories.AbstractRepository;

import java.util.List;

public abstract class StudentRepository extends AbstractRepository<Student,Long> {

    public abstract List<Student> findCourseId(Long courseId) throws PersistentException;
    public abstract Integer courseCount(Long studentId) throws PersistentException;
}
