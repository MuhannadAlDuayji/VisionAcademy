package online.visionacademy.model;

import online.visionacademy.dao.Identifiable;

import java.time.LocalDate;

public class Student implements Identifiable {

    private Long id;
    private Long nationalId;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public Student(){

    }


    public Student(Long id){
        this.id = id;
    }
    public Student(Long id, Long nationalId, String firstName, String lastName, LocalDate dob) {
        this.id = id;
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public Student(Long nationalId, String firstName, String lastName, LocalDate dob) {
        this(null,nationalId,firstName,lastName,dob);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {

        return "Student {" +
                "id=" + id +
                ", nationalId=" + nationalId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                '}';
    }
}
