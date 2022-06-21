package kz.issak.springCourseAlishev.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
    int id;
    @NotEmpty(message = "Full Name is should not be empty")
    @Size(min = 2, max = 40, message = "Full name should be be between 2 and 30 characters")
    private String fullName;
    @NotEmpty(message = "Date of birth is should not be empty")
    @Pattern(regexp = "\\d{2}.\\d{2}.\\d{4}", message = "date of Brithday should be like dd.mm.yyyy")
    private String dateOfBirth;

    public Person() {
    }

    public Person(int id, String fullName, String dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
