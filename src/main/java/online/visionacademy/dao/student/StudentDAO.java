package online.visionacademy.dao.student;

import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.model.Student;

public abstract class StudentDAO extends AbstractDAO<Student,Long> {

    public abstract Student getStudentWithHighestGPA();
}
