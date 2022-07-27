package online.visionacademy.service;

import online.visionacademy.dtos.CourseDTO;
import online.visionacademy.exceptions.CourseNotFoundException;
import online.visionacademy.exceptions.PersistenceException;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.exceptions.ValidationException;
import online.visionacademy.dtos.mappers.CourseMapper;
import online.visionacademy.model.Course;
import online.visionacademy.repositories.course.CourseRepository;
import online.visionacademy.repositories.course.CourseRepositoryImpl;
import online.visionacademy.repositories.student.StudentRepository;
import online.visionacademy.repositories.student.StudentRepositoryImpl;
import online.visionacademy.validators.LengthValidator;
import online.visionacademy.validators.NullValidator;
import online.visionacademy.validators.Validators;

import java.util.List;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(){
        courseRepository = new CourseRepositoryImpl();
        studentRepository= new StudentRepositoryImpl();
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
    public List<Course> getAllByIds(List<Long> ids) throws ServiceException {

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
           return !courseRepository.contains(id);
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
            return courseRepository.studentCount(id);
        } catch (PersistenceException e) {
            throw new ServiceException("Exception happen :D ... ");
        }
    }


    @Override
    public Course update(Course course) throws ServiceException, CourseNotFoundException {
        checkNull("Course", course);
        validateCourse(course);

        Long id = course.getId();
        if (contains(id)) {
            throw new CourseNotFoundException("Can't update course, course with id " + id + " not found");
        }
        try {
            //check if all students exist
            for (Long stdId : course.getStudentIds()) {
                if (!studentRepository.contains(stdId)) {
                    throw new ValidationException("Student with id " + id + " does not exist.");
                }
            }
            return courseRepository.update(course);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not update course");
        }
    }

    @Override
    public void removeById(Long id) throws ServiceException, CourseNotFoundException {
        try {
            if (contains(id)) {
                throw new CourseNotFoundException("Can't remove course, course with id " + id + " not found");
            }
            courseRepository.removeById(id);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not remove course.");
        }
    }

    @Override
    public void remove(Course course) throws ServiceException, CourseNotFoundException {
        checkNull("Course", course);
        removeById(course.getId());
    }

    @Override
    public void removeAllById(List<Long> ids) throws ServiceException, CourseNotFoundException {
        for (Long id : ids) {
            removeById(id);
        }
    }

    @Override
    public CourseDTO getCourseWithStudents(Long id) throws ServiceException {

        try {
            Course course = getById(id);

            CourseMapper courseMapper = new CourseMapper();
            CourseDTO courseDTO = courseMapper.mapToDTO(course);

            courseDTO.setStudentList(
                    studentRepository.findCourseId(id)
                            .stream()
                            .map(student -> student.getFirstName()+" "+student.getLastName()).
                            collect(Collectors.toList()
                            )
            );
            return courseDTO;
        } catch (ServiceException | PersistenceException e) {
            throw new ServiceException(e.getMessage());
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
