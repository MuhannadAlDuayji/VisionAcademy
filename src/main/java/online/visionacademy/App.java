package online.visionacademy;

import online.visionacademy.dao.course.CourseDAO;
import online.visionacademy.dao.course.OracleCourseDAO;
import online.visionacademy.dao.registration.OracleRegistrationDAO;
import online.visionacademy.dao.registration.RegistrationDAO;
import online.visionacademy.dao.student.OracleStudentDAO;
import online.visionacademy.dao.student.StudentDAO;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.model.Course;
import online.visionacademy.model.Registration;

import java.util.List;


public class App {

    public static void main(String[] args)  {

        StudentDAO studentDAO =new OracleStudentDAO();
        RegistrationDAO registrationDAO = new OracleRegistrationDAO();
        CourseDAO courseDAO = new OracleCourseDAO();

        try {

            System.out.println("=========Student=============");
            studentDAO.readAll().forEach(System.out::println);
            System.out.println("=========Course======");
            courseDAO.readAll().forEach(System.out::println);
            System.out.println("=========Registration=============");
            registrationDAO.readAll().forEach(System.out::println);


        } catch (DAOException e) {
            System.out.println(e);
        }


    }

}

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