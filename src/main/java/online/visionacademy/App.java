package online.visionacademy;

import net.datafaker.Faker;
import online.visionacademy.dao.oracledao.course.CourseDAO;
import online.visionacademy.dao.oracledao.course.OracleCourseDAO;
import online.visionacademy.dao.oracledao.registration.OracleRegistrationDAO;
import online.visionacademy.dao.oracledao.registration.RegistrationDAO;
import online.visionacademy.dao.oracledao.student.OracleStudentDAO;
import online.visionacademy.dao.oracledao.student.StudentDAO;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.exceptions.PersistentException;
import online.visionacademy.model.Course;
import online.visionacademy.model.Student;
import online.visionacademy.repositories.course.CourseRepository;
import online.visionacademy.repositories.course.CourseRepositoryImpl;
import online.visionacademy.repositories.student.StudentRepository;
import online.visionacademy.repositories.student.StudentRepositoryImpl;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class App {

    public static void main(String[] args)  {

        StudentRepository studentRepository = new StudentRepositoryImpl();
        CourseRepository courseRepository = new CourseRepositoryImpl();

        try {
            studentRepository.addAll(studentList());
            System.out.println("=======================");
            courseRepository.findAll().forEach(System.out::println);
            System.out.println("=======================");
            Course course = new Course("CD","CD CD","CD CD ...");
            course.addStudent(new Student(1L));
            course.addStudent(new Student(2L));
            course.addStudent(new Student(3L));
            course.addStudent(new Student(21L));
            course.addStudent(new Student(22L));


            courseRepository.add(course);
            System.out.println("=======================");


        } catch (PersistentException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Student> studentList(){
        ArrayList<Student> students = new ArrayList<>();
        Faker dataFaker = new Faker();

        for(int i = 0 ; i < 5 ; i++){
            students.add(new Student(Long.parseLong(dataFaker.idNumber().invalid().replaceAll("-",""))
            ,dataFaker.name().firstName()
            ,dataFaker.name().lastName()
            ,LocalDate.of(1990,1,1)));
        }

        return students;
    }
}

/*
* StudentRepository studentRepository = new StudentRepositoryImpl();

        try {
            studentRepository.findAll().stream()
                            .sorted(Comparator.comparingLong(Student::getId))
                            .forEach(System.out::println);

            System.out.println(studentRepository.courseCount(1L));
            System.out.println(studentRepository.findAllById(Arrays.asList(1L)));
            System.out.println(studentRepository.courseCount(3L));

            System.out.println(studentRepository.findCourseId(3L));




        } catch (PersistentException e) {
            System.out.println(e);
        }*/



/*
    StudentDAO studentDAO =new OracleStudentDAO();
    RegistrationDAO registrationDAO = new OracleRegistrationDAO();
    CourseDAO courseDAO = new OracleCourseDAO();

            System.out.println("=========Student=============");
            studentDAO.readAll().forEach(System.out::println);
            System.out.println("=========Course======");
            courseDAO.readAll().forEach(System.out::println);
            System.out.println("=========Registration=============");
           registrationDAO.readAll().forEach(System.out::println);
*/

/*
studentDAO.insert(new Student(1002002020L
//                  ,"Muhannad","Alduayji", LocalDate.of(1997,6,30)));
//          studentDAO.insert(new Student(1002000909L
//                  ,"Raed","Yasser", LocalDate.of(1997,6,30)));
//          studentDAO.insert(new Student(1002000919L
//                  ,"Khalid","Mareom", LocalDate.of(1997,6,30)));
//              studentDAO.insert(new Student(1002001111L
//                      ,"Gorge","Smaha", LocalDate.of(1997,6,30)));

          Optional<Student> data = studentDAO.readById(1L);
          Student temp = data.get();

          temp.setNationalId(1001009999L);
          studentDAO.update(temp);
          List<Long> ids = new ArrayList<>();
          ids.add(1L);
          ids.add(3L);

          List<Student> studentList = studentDAO.readAllById(ids);
          for (Student s:studentList) {
              System.out.println(s);
          }
 */


// =====================================================================
/*
*   CourseDAO courseDAO= new OracleCourseDAO();

      try {

        System.out.println("read all :: ");
        courseDAO.readAll().forEach(System.out::println);
        System.out.println("=====================");
        System.out.println("read by ids :: ");
        courseDAO.readAllById(Arrays.asList(6L,3L)).forEach(System.out::println);
        System.out.println("=====================\n\n");

        System.out.println(courseDAO.ddf());
        StudentDAO studentDAO = new OracleStudentDAO();
        System.out.println(studentDAO.ddf()+"\n");

        System.out.println("=====================");
        System.out.println("read all :: ");
        courseDAO.readAll().forEach(System.out::println);

      }catch (Exception e) {
          System.err.println(e.toString());
      }*/