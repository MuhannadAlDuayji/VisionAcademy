package online.visionacademy.repositories.course;

import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.dao.oracledao.course.CourseDAO;
import online.visionacademy.dao.oracledao.course.OracleCourseDAO;
import online.visionacademy.dao.oracledao.registration.OracleRegistrationDAO;
import online.visionacademy.dao.oracledao.registration.RegistrationDAO;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.exceptions.PersistentException;
import online.visionacademy.model.Course;
import online.visionacademy.model.Registration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseRepositoryImpl extends CourseRepository{

    private CourseDAO courseDAO;
    private RegistrationDAO registrationDAO;


    public CourseRepositoryImpl(){
        this.courseDAO = (CourseDAO) getAbstractDAO();
        this.registrationDAO = new OracleRegistrationDAO();
    }
    @Override
    public AbstractDAO getAbstractDAO() {
        return new OracleCourseDAO();
    }

    @Override
    public List<Course> findStudentId(Long studentId) throws PersistentException {

        List<Course> courseList = new ArrayList<>();

        try {
            List<Registration> registrationList = registrationDAO.findStudentById(studentId);
            for (Registration registration: registrationList) {
                Optional<Course> optionalCourse = courseDAO.readById(registration.getId());
                optionalCourse.ifPresent(courseList::add);

            }
        } catch (DAOException e) {
            
            throw new PersistentException(e.getMessage(),e);
        }

        return courseList;
    }

    @Override
    public void removeAllRegistration(Long courseId) throws PersistentException {

        try {

            for (Registration reg: registrationDAO.findCourseById(courseId)) {
                    registrationDAO.delete(reg.getId());
            }

        } catch (DAOException e) {
            throw new PersistentException(e.getMessage(),e);
        }
    }

    @Override
    public Integer studentCount(Long courseId) throws PersistentException {


        try {
            return registrationDAO.findCourseById(courseId).size();
        } catch (DAOException e) {
            throw new PersistentException(e.getMessage(),e);
        }

    }

    @Override
    public boolean isRegistered(Long courseId, Long studentId) throws PersistentException {

        try {

            List<Registration> registrationList = registrationDAO.findCourseById(courseId);
            for (Registration reg:registrationList){
                if (reg.getStudentId().equals(studentId)) {
                    return true;
                }
            }

        } catch (DAOException e) {
            throw new PersistentException(e.getMessage(),e);
        }
        return false;
    }

    @Override
    public void register(Long courseId, Long studentId) throws PersistentException {

        if(isRegistered(courseId,studentId)) {
            System.out.println("Already exists.");
            return;
        }
        try {
            registrationDAO.insert(new Registration(studentId,courseId));
        } catch (DAOException e) {
            throw new PersistentException(e.getMessage(),e);
        }
    }

    @Override
    public void deRegistered(Long courseId, Long studentId) throws PersistentException {

        try {

            List<Registration> registrationList = registrationDAO.findCourseById(courseId);

            for (Registration reg:registrationList){
                if (reg.getStudentId().equals(studentId)) {
                    registrationDAO.delete(reg.getId());
                    return;
                }
            }
        } catch (DAOException e) {
            throw new PersistentException(e.getMessage(),e);
        }
    }

    @Override
    public Optional<Course> findById(Long id) throws PersistentException {

        Optional<Course> optionalCourse = super.findById(id);
        try {
            if(optionalCourse.isPresent()){
                List<Registration> registrationList = registrationDAO.findCourseById(optionalCourse.get().getId());
                List<Long> ids = registrationList.stream().map(Registration::getStudentId).collect(Collectors.toList());
                optionalCourse.get().setStudentIds(ids);
            }
        }catch (DAOException e){
            throw new PersistentException(e.getMessage(),e);
        }

        return optionalCourse;
    }

    @Override
    public Course add(Course entity) throws PersistentException {

        try {
            if(!courseDAO.readById(entity.getId()).isPresent()) {
                super.add(entity);
                System.out.println("Course Added ...");
            }
        } catch (DAOException e) {
            throw new PersistentException(e.getMessage(),e);
        }

        System.out.println("Finished from course ... ");

        for (Long id: entity.getStudentIds()) {
            if(!isRegistered(entity.getId(),id)){
                try {
                    registrationDAO.insert(new Registration(id,entity.getId()));
                } catch (DAOException e) {
                    throw new PersistentException(e.getMessage(),e);
                }
            }
        }

        return entity;
    }

}
