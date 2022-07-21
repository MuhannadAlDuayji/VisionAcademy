package online.visionacademy.service;

import online.visionacademy.exceptions.CourseNotFoundException;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.exceptions.StudentNotFoundException;
import online.visionacademy.model.Student;
import java.util.List;

public interface StudentService {

    // save
    public abstract Student add(Student student) throws ServiceException;
    public abstract List<Student> add(List<Student> students) throws ServiceException;

    // read
    public abstract Student getById(Long id) throws ServiceException, StudentNotFoundException;
    public abstract List<Student> getAll() throws ServiceException;
    public abstract List<Student> getAllById(List<Long> ids) throws ServiceException;
    public abstract List<Student> getByFirstName(String firstName) throws ServiceException;
    public abstract List<Student> getAllByCourse(Long id) throws ServiceException;

    public abstract List<Student> getStudentsWithCourses(Integer count) throws ServiceException;
    public abstract List<Student> getStudentsWithNoCourses() throws ServiceException;

    public abstract boolean contains(Long id) throws ServiceException;

    public abstract Integer count() throws ServiceException;

    public abstract Integer courseCount(Long id) throws ServiceException;

    // update
    public abstract Student update(Student student) throws ServiceException, StudentNotFoundException;


    // remove
    public abstract void removeById(Long id) throws ServiceException, StudentNotFoundException;
    public abstract void removeById(Student student) throws ServiceException, StudentNotFoundException;
    public abstract void removeAllById(List<Long> ids) throws ServiceException, StudentNotFoundException;

    // regs
    public abstract void registerForCourse(Long courseId, Long studentId) throws ServiceException, StudentNotFoundException, CourseNotFoundException;
    public abstract void cancelCourse(Long courseId, Long studentId) throws ServiceException, StudentNotFoundException, CourseNotFoundException;
    public abstract void cancelAll(Long studentId) throws ServiceException, StudentNotFoundException, CourseNotFoundException;


}

