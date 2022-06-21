package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.models.Book;

public interface BooksEditingService {
    public void edit(int id, Book book);
    public void setPersonId(int id, int personId);

    public void freeBook(int bookId);
}
