package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.dao.BooksDAO;
import kz.issak.springCourseAlishev.models.Book;
import org.springframework.stereotype.Service;

@Service
public class BooksCreatingServiceImpl implements BooksCreatingService {

    private final BooksDAO booksDAO;

    public BooksCreatingServiceImpl(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @Override
    public void createNewBook(Book book) {
        booksDAO.addBook(book);
    }
}
