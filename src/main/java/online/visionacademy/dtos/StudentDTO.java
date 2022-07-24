package online.visionacademy.dtos;

import java.util.List;

public class StudentDTO {

    private String name;
    private Long nationalId;
    private List<String> courseList;

    public StudentDTO() {
    }

    public StudentDTO(String name, Long nationalId, List<String> courseList) {
        this.name = name;
        this.nationalId = nationalId;
        this.courseList = courseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "name='" + name + '\'' +
                ", nationalId=" + nationalId +
                ", courseList=" + courseList +
                '}';
    }
}
