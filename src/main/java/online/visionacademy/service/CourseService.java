package online.visionacademy.service;

import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.exceptions.CourseNotFoundException;
import online.visionacademy.model.Course;
import online.visionacademy.model.Course;

import java.util.List;

public interface CourseService {

    public abstract Course add(Course course) throws ServiceException;
    public abstract List<Course> add(List<Course> courses) throws ServiceException;

    // read
    public abstract Course getById(Long id) throws ServiceException, CourseNotFoundException;
    public abstract List<Course> getAll() throws ServiceException;
    public abstract List<Course> getAllById(List<Long> ids) throws ServiceException;
    public abstract List<Course> getAllByCourse(Long id) throws ServiceException;

    public abstract List<Course> getCoursesWithCourses(Integer count) throws ServiceException;
    public abstract List<Course> getCoursesWithNoCourses() throws ServiceException;

}
