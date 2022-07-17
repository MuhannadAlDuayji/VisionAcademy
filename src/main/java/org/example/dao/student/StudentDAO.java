package org.example.dao.student;

import org.example.dao.AbstractDAO;
import org.example.model.Student;

public abstract class StudentDAO extends AbstractDAO<Student,Long> {

    public abstract Student getStudentWithHighestGPA();
}
