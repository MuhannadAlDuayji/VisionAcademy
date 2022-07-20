package online.visionacademy.repositories.student;

import online.visionacademy.exceptions.PersistenceException;
import online.visionacademy.model.Student;
import online.visionacademy.repositories.AbstractRepository;

import java.util.List;

public abstract class StudentRepository extends AbstractRepository<Student,Long> {

    public abstract List<Student> findCourseId(Long courseId) throws PersistenceException;
    public abstract Integer courseCount(Long studentId) throws PersistenceException;
}
