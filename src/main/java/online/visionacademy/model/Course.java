package online.visionacademy.model;

import online.visionacademy.dao.Identifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Course implements Identifiable {

    private Long id;
    private String code;
    private String name;
    private String description;


    private List<StudentRef> studentRefList = new ArrayList<>();

    public Course(){
    }

    public Course(Long id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Course(String code, String name, String description){
        this(null,code,name,description);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", studentRefList=" + studentRefList +
                '}';
    }

    public void addStudent(Student student){
        this.studentRefList.add(new StudentRef(student.getId()));
    }
    public void setStudentIds(List<Long> ids) {
        for (Long studentId:ids) {
            this.studentRefList.add(new StudentRef(studentId));
        }
    }
    public List<Long> getStudentIds(){
        return studentRefList.stream()
                .map(StudentRef::getStudentId)
                .collect(Collectors.toList());
    }
}
