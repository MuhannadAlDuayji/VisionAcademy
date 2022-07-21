package online.visionacademy.repositories.course;

import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.dao.oracledao.course.CourseDAO;
import online.visionacademy.dao.oracledao.course.OracleCourseDAO;
import online.visionacademy.dao.oracledao.registration.OracleRegistrationDAO;
import online.visionacademy.dao.oracledao.registration.RegistrationDAO;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.exceptions.PersistenceException;
import online.visionacademy.model.Course;
import online.visionacademy.model.Registration;

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
    public List<Course> findByStudentId(Long studentId) throws PersistenceException {

        List<Course> courseList = new ArrayList<>();

        try {
            List<Registration> registrationList = registrationDAO.findStudentById(studentId);
            for (Registration registration: registrationList) {
                Optional<Course> optionalCourse = courseDAO.readById(registration.getCourseId());
                optionalCourse.ifPresent(courseList::add);
            }
        } catch (DAOException e) {
            
            throw new PersistenceException(e.getMessage(),e);
        }

        return courseList;
    }

    @Override
    public void removeAllRegistration(Long courseId) throws PersistenceException {

        try {

            for (Registration reg: registrationDAO.findCourseById(courseId)) {
                    registrationDAO.delete(reg.getId());
            }

        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public Integer studentCount(Long courseId) throws PersistenceException {


        try {
            return registrationDAO.findCourseById(courseId).size();
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }

    }

    @Override
    public boolean isRegistered(Long courseId, Long studentId) throws PersistenceException {

        try {

            List<Registration> registrationList = registrationDAO.findCourseById(courseId);
            for (Registration reg:registrationList){
                if (reg.getStudentId().equals(studentId)) {
                    return true;
                }
            }

        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
        return false;
    }

    @Override
    public void register(Long courseId, Long studentId) throws PersistenceException {

        if(isRegistered(courseId,studentId)) {
            System.out.println("Already exists.");
            return;
        }
        try {
            registrationDAO.insert(new Registration(studentId,courseId));
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public void deRegistered(Long courseId, Long studentId) throws PersistenceException {

        try {

            List<Registration> registrationList = registrationDAO.findCourseById(courseId);

            for (Registration reg:registrationList){
                if (reg.getStudentId().equals(studentId)) {
                    registrationDAO.delete(reg.getId());
                    return;
                }
            }
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public List<Course> findAll() throws PersistenceException {

        List<Course> courses = super.findAll();
        for (Course course: courses) {
            try {
                getStudentIdsForCourse(course);
            } catch (DAOException e) {
                throw new PersistenceException(e.getMessage(),e);
            }
        }

        return courses;
    }

    @Override
    public List<Course> findAllById(List<Long> longs) throws PersistenceException {

        List<Course> courses = super.findAllById(longs);
        for (Course course: courses) {
            try {
                getStudentIdsForCourse(course);
            } catch (DAOException e) {
                throw new PersistenceException(e.getMessage(),e);
            }
        }

         return courses;
    }

    @Override
    public Optional<Course> findById(Long id) throws PersistenceException {

        Optional<Course> optionalCourse = super.findById(id);
        try {
            if(optionalCourse.isPresent()){
                getStudentIdsForCourse(optionalCourse.get());
            }
        }catch (DAOException e){
            throw new PersistenceException(e.getMessage(),e);
        }

        return optionalCourse;
    }

    @Override
    public Course add(Course entity) throws PersistenceException {

        try {
            if(!courseDAO.readById(entity.getId()).isPresent()) {
                super.add(entity);
                System.out.println("Course Added ...");
            }else {
                System.out.println("Course Already Added will be update...");
                update(entity);
                System.out.println("Course has been update...");
                return entity;
            }
        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }

        for (Long id: entity.getStudentIds()) {
            register(entity.getId(),id);
        }

        return entity;
    }

    @Override
    public Course update(Course entity) throws PersistenceException {

        super.update(entity);
        try {


            List<Registration> regList = registrationDAO.findCourseById(entity.getId());
            for (Registration reg:regList) {
                // this will edit in future we need to call function 1 time for delete any student related for course
                registrationDAO.delete(reg.getId());
            }
            for (Long id: entity.getStudentIds()) {
                register(entity.getId(),id);
            }

        } catch (DAOException e) {
            throw new PersistenceException(e.getMessage(),e);
        }

        return entity;
    }

    private void getStudentIdsForCourse(Course course) throws DAOException{

        List<Registration> registrationList = registrationDAO.findCourseById(course.getId());
        List<Long> ids = registrationList.stream().map(Registration::getStudentId).collect(Collectors.toList());
        course.setStudentIds(ids);

    }

}
