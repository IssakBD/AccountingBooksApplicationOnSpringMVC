package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.models.Book;

import java.util.List;

public interface BooksListingService {
    public List<Book> getBooksList();

    public Book getBook(int id);

    public List<Book> getBookListByPersonId(int id);

    public int getPersonIdByBookId(int id);
}
