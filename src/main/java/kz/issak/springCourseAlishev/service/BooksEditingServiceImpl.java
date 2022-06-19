package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.dao.BooksDAO;
import kz.issak.springCourseAlishev.models.Book;
import org.springframework.stereotype.Service;

@Service
public class BooksEditingServiceImpl implements BooksEditingService{

    private final BooksDAO booksDAO;

    public BooksEditingServiceImpl(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @Override
    public void edit(int id, Book book) {
        booksDAO.editBook(id, book);
    }
}
