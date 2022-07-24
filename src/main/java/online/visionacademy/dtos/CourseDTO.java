package online.visionacademy.dtos;

import java.util.List;

public class CourseDTO {

    private String code;
    private String name;

    private List<String> studentList;


    public CourseDTO() {
    }

    public CourseDTO(String code, String name, List<String> studentList) {
        this.code = code;
        this.name = name;
        this.studentList = studentList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}
