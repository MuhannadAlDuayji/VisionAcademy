package online.visionacademy.service;

import online.visionacademy.exceptions.CourseNotFoundException;
import online.visionacademy.exceptions.PersistenceException;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.model.Course;
import online.visionacademy.repositories.course.CourseRepository;
import online.visionacademy.repositories.course.CourseRepositoryImpl;
import online.visionacademy.validators.LengthValidator;
import online.visionacademy.validators.NullValidator;
import online.visionacademy.validators.Validators;

import java.util.List;

public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    public CourseServiceImpl(){
        courseRepository = new CourseRepositoryImpl();
    }

    @Override
    public Course add(Course course) throws ServiceException {


        try {
            checkNull("Course",course);
            checkNull("Course code",course.getCode());
            checkNull("Course name",course.getName());
            checkNull("Course description",course.getDescription());
            validateCourse(course);
            return courseRepository.add(course);
        } catch (PersistenceException e) {
            throw new ServiceException("Could not save course");
        }
    }

    @Override
    public List<Course> add(List<Course> courses) throws ServiceException {

        for (Course course:courses) {
                add(course);
        }
        return courses;
    }

    @Override
    public Course getById(Long id) throws ServiceException, CourseNotFoundException {

        try {
            return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
        } catch (PersistenceException e) {
            throw new ServiceException("Error happen ...");
        }
    }

    @Override
    public List<Course> getAll() throws ServiceException {

        try {
            return courseRepository.findAll();
        } catch (PersistenceException e) {
            throw new ServiceException("Error happen ...");
        }
    }

    @Override
    public List<Course> getAllById(List<Long> ids) throws ServiceException {

        try {
            return courseRepository.findAllById(ids);
        } catch (PersistenceException e) {
            throw new ServiceException("Error happen ...");
        }
    }


    @Override
    public Course getByCode(String code) throws ServiceException, CourseNotFoundException {

        try {
            List<Course> course = courseRepository.findByColumn("code",code);
            if (course.size() == 0)
                throw new CourseNotFoundException("Course with code : "+code);
            return course.get(0);
        } catch (PersistenceException e) {
            throw new ServiceException("Error happen ...");
        }
    }

    @Override
    public List<Course> getAllByStudent(Long studentId) throws ServiceException {

        try {
            return courseRepository.findByStudentId(studentId);
        } catch (PersistenceException e) {
            throw new ServiceException("Course for student not found....");
        }
    }

    @Override
    public boolean contains(Long id) throws ServiceException {

        try {
           return courseRepository.contains(id);
        } catch (PersistenceException e) {
            throw new ServiceException("Course not found");
        }
    }

    @Override
    public Integer count() throws ServiceException {

        try {
            return courseRepository.count();
        } catch (PersistenceException e) {
            throw new ServiceException("Error");
        }
    }

    @Override
    public Integer studentCount(Long id) throws ServiceException {
        try {
            return courseRepository.studentCount(id.longValue());
        } catch (PersistenceException e) {
            throw new ServiceException("Exception happen :D ... ");
        }
    }

    public void checkNull(String field, Object object){

        Validators validator = new NullValidator(field,object);
        validator.validate();
        validator = null;

    }

    public void validateCourse(Course course){

        String code = course.getCode();
        String name = course.getName();
        String description = course.getDescription();

        LengthValidator validator = new LengthValidator("Course code",code,2,3);
        validator.validate();

        validator.setValues("Course name",name,2,10);
        validator.validate();

        validator.setValues("Course description",description,2,4000);
        validator.validate();

    }

}
