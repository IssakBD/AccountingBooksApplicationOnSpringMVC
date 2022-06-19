package kz.issak.springCourseAlishev.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Book's name is should not be empty")
    @Size(min = 2, max = 30, message = "Book's name size should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Author's name is should not be empty")
    @Size(min = 2, max = 30, message = "Author's name size should be between 2 and 30 characters")
    private String authorName;

    @Min(1000)
    @Max(2023)
    private int dateOfPublish;

    public Book() {
    }

    public Book(int id, String name, String authorName, int dateOfPublish) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.dateOfPublish = dateOfPublish;
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
}
