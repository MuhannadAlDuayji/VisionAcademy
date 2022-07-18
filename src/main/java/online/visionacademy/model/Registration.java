package online.visionacademy.model;

import online.visionacademy.dao.Identifiable;

import java.util.List;
import java.util.stream.Collectors;

public class Registration implements Identifiable {

    private Long id;
    private Long studentId;
    private Long courseId;

    private List<StudentRef> studentRefList;

    public Registration(Long id, Long studentId, Long courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Registration(Long studentId, Long courseId) {
        this(null,studentId,courseId);
    }

    public Registration() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }


    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
