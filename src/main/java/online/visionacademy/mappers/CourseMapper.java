package online.visionacademy.mappers;

import online.visionacademy.dtos.CourseDTO;
import online.visionacademy.model.Course;

public class CourseMapper implements Mapper<Course, CourseDTO> {

    @Override
    public CourseDTO mapToDTO(Course entity) {

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setName(entity.getName());
        courseDTO.setCode(entity.getCode());

        return courseDTO;
    }

    @Override
    public Course mapToEntity(CourseDTO entityDTO) {

        Course course = new Course();

        course.setName(entityDTO.getName());
        course.setCode(entityDTO.getCode());

        return course;
    }
}
