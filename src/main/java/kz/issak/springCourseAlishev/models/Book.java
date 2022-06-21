package kz.issak.springCourseAlishev.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    private int personId;
    @NotEmpty(message = "Book's name is should not be empty")
    @Size(min = 2, max = 30, message = "Book's name size should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Author's name is should not be empty")
    @Size(min = 2, max = 30, message = "Author's name size should be between 2 and 30 characters")
    private String authorName;

    @Min(value = 1000, message = "Min value is 1000")
    @Max(value = 2023, message = "Max value is 2023")
    private int dateOfPublish;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", personId=" + personId +
                ", name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                ", dateOfPublish=" + dateOfPublish +
                '}';
    }

    public Book() {
    }

    public Book(int id, String name, String authorName, int dateOfPublish, int personId) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.dateOfPublish = dateOfPublish;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(int dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
