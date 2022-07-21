package online.visionacademy.service;

import online.visionacademy.exceptions.*;
import online.visionacademy.model.Course;
import online.visionacademy.model.Student;
import online.visionacademy.repositories.course.CourseRepository;
import online.visionacademy.repositories.course.CourseRepositoryImpl;
import online.visionacademy.repositories.student.StudentRepository;
import online.visionacademy.repositories.student.StudentRepositoryImpl;
import online.visionacademy.validators.LengthValidator;
import online.visionacademy.validators.NullValidator;
import online.visionacademy.validators.Validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(){
        studentRepository = new StudentRepositoryImpl();
        courseRepository = new CourseRepositoryImpl();
    }

    @Override
    public Student add(Student student) throws ServiceException {

        checkNull("Student",student);
        validateStudent(student);

        String nationalityId = student.getNationalId().toString();

        try {

            List<Student> students=studentRepository.findByColumn("NATIONAL_ID",nationalityId);
            if(students.size() > 0){
                throw new ValidationException("Student with national ID "+nationalityId+" exist.");
            }

            return studentRepository.add(student);
        } catch (PersistenceException e) {
            throw new ServiceException("Could not save student.");
        }
    }

    @Override
    public List<Student> add(List<Student> students) throws ServiceException {

        for (Student student : students) {
            add(student);
        }

        return students;
    }

    @Override
    public Student getById(Long id) throws ServiceException, StudentNotFoundException {


        try {
            checkNull("Id",id);
            Optional<Student> optionalStudent = studentRepository.findById(id);

            if (!optionalStudent.isPresent())
                throw new StudentNotFoundException("Student with id "+id+" not found");
            return optionalStudent.get();
        } catch (PersistenceException e) {
            throw new ServiceException("Could not fetch student.");
        }
    }

    @Override
    public List<Student> getAll() throws ServiceException {

        try {
            return studentRepository.findAll();
        }catch (PersistenceException e){
            throw new ServiceException("Could not fetch the Students");
        }
    }

    @Override
    public List<Student> getAllById(List<Long> ids) throws ServiceException {

        try {
            return studentRepository.findAllById(ids);
        }catch (PersistenceException e){
            throw new ServiceException("Could not fetch the IDs");
        }
    }

    @Override
    public List<Student> getByFirstName(String firstName) throws ServiceException {

        try {
            return studentRepository.findByColumn("FIRST_NAME",firstName);
        }catch (PersistenceException e){
            throw new ServiceException("Could not find "+firstName);
        }
    }

    @Override
    public List<Student> getAllByCourse(Long id) throws ServiceException {

        try {
            checkNull("Course ID",id);
            return studentRepository.findCourseId(id);
        }catch (PersistenceException e){
            throw new ServiceException("Could not fetch the Course ID");
        }

    }


    // this method will return the student list with number of count Courses
    @Override
    public List<Student> getStudentsWithCourses(Integer count) throws ServiceException {

        List<Student> studentList = new ArrayList<>();

            checkNull("Courses Count",count);

            for (Student student:getAll()) {

                if(courseCount(student.getId()).equals(count))
                    studentList.add(student);

            }
        return studentList;
    }

    @Override
    public List<Student> getStudentsWithNoCourses() throws ServiceException {
        return getStudentsWithCourses(0);
    }

    @Override
    public boolean contains(Long id) throws ServiceException {

        try {
            return studentRepository.contains(id);
        } catch (PersistenceException e) {
            throw new ServiceException("Could not work well.");
        }
    }

    @Override
    public Integer count() throws ServiceException {

        try {
            return studentRepository.count();
        } catch (PersistenceException e) {
            throw new ServiceException("Could not work well.");
        }
    }

    @Override
    public Integer courseCount(Long id) throws ServiceException {

        try {
            checkNull("Course Id",id);
            return studentRepository.courseCount(id);
        }catch (PersistenceException e){
            throw new ServiceException("Could not fetch Course count");
        }
    }

    @Override
    public Student update(Student student) throws ServiceException, StudentNotFoundException {

        try {

            checkNull("Student",student);
            checkNull("Id",student.getId());
            checkNull("First Name",student.getFirstName());
            checkNull("Last Name",student.getLastName());
            checkNull("National Id",student.getNationalId());

            if(!studentRepository.findById(student.getId()).isPresent())
                throw new StudentNotFoundException("Could not update student, student with id "+student.getId()+" not found.");
            return studentRepository.update(student);
        }catch (PersistenceException e){
            throw new ServiceException("Could not fetch Course count");
        }
    }

    @Override
    public void removeById(Long id) throws ServiceException, StudentNotFoundException {

        try {
            checkNull("Student Id",id);
            if (!contains(id))
                throw new StudentNotFoundException("Could not update student, student with id "+id+" not found.");
            studentRepository.removeById(id);
        }catch (PersistenceException e){
            throw new ServiceException("Could not fetch Student Id");
        }
    }

    @Override
    public void removeById(Student student) throws ServiceException, StudentNotFoundException {
        try {
            checkNull("Student",student);
            checkNull("Student Id",student.getId());
            if (!contains(student.getId()))
                throw new StudentNotFoundException("Could not update student, student with id "+student.getId()+" not found.");
            studentRepository.removeById(student.getId());
        }catch (PersistenceException e){
            throw new ServiceException("Could not fetch Student Id");
        }
    }

    @Override
    public void removeAllById(List<Long> ids) throws ServiceException, StudentNotFoundException {

            for (Long id:ids) {
                checkNull("Student Id", id);
                removeById(id);
            }
    }

    @Override
    public void registerForCourse(Long courseId, Long studentId) throws ServiceException, StudentNotFoundException, CourseNotFoundException {

        checkNull("Course Id",courseId);
        checkNull("Student Id",studentId);

        try {

            if (!contains(studentId))
                throw new StudentNotFoundException("Could not found Student id "+studentId);

            if(!courseRepository.contains(courseId))
                throw new CourseNotFoundException("Could not found Course id "+courseId);

            if (courseCount(studentId) > 5)
                throw new ValidationException("Can not register more than 5");

            if (courseRepository.isRegistered(courseId,studentId))
                throw new ValidationException("Student already registered");

            courseRepository.register(courseId,studentId);

        } catch (PersistenceException e) {
            throw new ServiceException("Could not register student");
        }


    }

    @Override
    public void cancelCourse(Long courseId, Long studentId) throws ServiceException, StudentNotFoundException, CourseNotFoundException {

        checkNull("Course Id",courseId);
        checkNull("Student Id",studentId);

        try {
            courseRepository.deRegistered(courseId,studentId);

        }catch (PersistenceException e){
            throw new ServiceException("Could not Deregister");
        }

    }

    @Override
    public void cancelAll(Long studentId) throws ServiceException, StudentNotFoundException, CourseNotFoundException {

        try {
            checkNull("Student Id ",studentId);
            for (Course course :courseRepository.findByStudentId(studentId)){
                cancelCourse(course.getId(),studentId);
            }

        } catch (PersistenceException e) {
            throw new ServiceException("Could not cancel");
        }

    }


    public void checkNull(String field, Object object){

        Validators validator = new NullValidator(field,object);
        validator.validate();
        validator = null;

    }

    public void validateStudent(Student student){

        String nationalId = student.getNationalId().toString();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();

        LengthValidator validator = new LengthValidator("National ID",nationalId,10,11);
        validator.validate();

        validator.setValues("First name",firstName,2,10);
        validator.validate();

        validator.setValues("Last name",lastName,2,10);
        validator.validate();



    }

}
