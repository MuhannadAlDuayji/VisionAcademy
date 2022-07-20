package online.visionacademy.repositories.student;

import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.dao.oracledao.registration.OracleRegistrationDAO;
import online.visionacademy.dao.oracledao.registration.RegistrationDAO;
import online.visionacademy.dao.oracledao.student.OracleStudentDAO;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.exceptions.PersistenceException;
import online.visionacademy.model.Registration;
import online.visionacademy.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl extends StudentRepository{

    private RegistrationDAO regDAO;

    public StudentRepositoryImpl(){
        regDAO = new OracleRegistrationDAO();
    }

    @Override
    public AbstractDAO getAbstractDAO() {
        return new OracleStudentDAO();
    }


    @Override
    public void removeById(Long studentId) throws PersistenceException {

        try {
            boolean isExists = contains(studentId);

            if (isExists) {
                List<Registration> regList = regDAO.findStudentById(studentId);
                for (Registration reg :regList) {
                        regDAO.delete(reg.getId());
                }
            }

        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
        super.removeById(studentId);
    }

    @Override
    public List<Student> findCourseId(Long courseId) throws PersistenceException {

        List<Student> studentList = new ArrayList<>();

        try{

            List<Registration> regList = regDAO.findCourseById(courseId);
            for (Registration reg:regList) {
                Optional<Student> optionalStudent = super.findById(reg.getStudentId());
                optionalStudent.ifPresent(studentList::add);
            }

        }catch (DAOException e){
                throw new PersistenceException(e.getMessage(),e);
        }

        return studentList;
    }

    @Override
    public Integer courseCount(Long studentId) throws PersistenceException {

        int count = 0;

        try {
            count = regDAO.findStudentById(studentId).size();
        }catch (DAOException e){
            throw new PersistenceException(e.getMessage(),e);
        }

        return count;
    }
}
