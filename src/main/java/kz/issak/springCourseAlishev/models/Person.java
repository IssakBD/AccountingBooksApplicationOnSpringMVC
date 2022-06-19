package kz.issak.springCourseAlishev.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    int id;
    @NotEmpty(message = "Full Name is should not be empty")
    @Size(min = 2, max = 30, message = "Full name should be be between 2 and 30 characters")
    private String fullName;
    @NotEmpty(message = "Date of birth is should not be empty")
    @Size(min = 10, max = 10, message = "Date of birth is not correct")
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
}
