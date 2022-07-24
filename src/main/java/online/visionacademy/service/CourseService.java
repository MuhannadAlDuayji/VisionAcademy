package online.visionacademy.service;

import online.visionacademy.dtos.CourseDTO;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.exceptions.CourseNotFoundException;
import online.visionacademy.model.Course;

import java.util.List;

public interface CourseService {

    public abstract Course add(Course course) throws ServiceException;
    public abstract List<Course> add(List<Course> courses) throws ServiceException;

    // read
    public abstract Course getById(Long id) throws ServiceException, CourseNotFoundException;
    public abstract Course getByCode(String code) throws ServiceException, CourseNotFoundException;
    public abstract List<Course> getAll() throws ServiceException;
    public abstract List<Course> getAllByStudent(Long studentId) throws ServiceException;
    public abstract List<Course> getAllByIds(List<Long> ids) throws ServiceException;


    public abstract boolean contains(Long id) throws ServiceException;

    // counter
    public abstract Integer count()throws ServiceException;
    public abstract Integer studentCount(Long id)throws ServiceException;

    //update
    public abstract Course update(Course course) throws ServiceException, CourseNotFoundException;

    //remove
    public abstract void removeById(Long id) throws ServiceException, CourseNotFoundException;

    public abstract void remove(Course course) throws ServiceException, CourseNotFoundException;

    public abstract void removeAllById(List<Long> ids) throws ServiceException, CourseNotFoundException;


    public abstract CourseDTO getCourseWithStudents(Long id) throws ServiceException;


}
