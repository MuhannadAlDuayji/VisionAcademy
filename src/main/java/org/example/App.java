package org.example;

import org.example.dao.course.CourseDAO;
import org.example.dao.course.OracleCourseDAO;
import org.example.dao.registration.OracleRegistrationDAO;
import org.example.dao.registration.RegistrationDAO;
import org.example.dao.student.OracleStudentDAO;
import org.example.dao.student.StudentDAO;
import org.example.exceptions.DAOException;
import org.example.exceptions.DataSourceException;
import org.example.model.Registration;

import java.util.Arrays;
import java.util.List;


public class App {

    public static void main(String[] args)  {

      RegistrationDAO registrationDAO = new OracleRegistrationDAO();

      try {


        List<Registration> rs = registrationDAO.findStudentById(1L);
        for(Registration r:rs)
            System.out.println(r);
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