package online.visionacademy.dao.oracledao.student;

import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.dao.oracledao.OracleDAO;
import online.visionacademy.model.Student;

public abstract class StudentDAO extends OracleDAO<Student,Long> {

    public abstract Student getStudentWithHighestGPA();
}
