package online.visionacademy.dtos.mappers;

import online.visionacademy.dtos.StudentDTO;
import online.visionacademy.model.Student;

public class StudentMapper implements Mapper<Student, StudentDTO>{


    @Override
    public StudentDTO mapToDTO(Student entity) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(entity.getFirstName()+" "+entity.getLastName());
        studentDTO.setNationalId(entity.getNationalId());

        return studentDTO;
    }

    @Override
    public Student mapToEntity(StudentDTO entityDTO) {

        Student student = new Student();
        student.setFirstName(entityDTO.getName().substring(0,entityDTO.getName().indexOf(' ')));
        student.setLastName(
                entityDTO.getName()
                        .substring(entityDTO.getName().indexOf(' ')+1)
        );
        student.setNationalId(entityDTO.getNationalId());

        return student;
    }
}
