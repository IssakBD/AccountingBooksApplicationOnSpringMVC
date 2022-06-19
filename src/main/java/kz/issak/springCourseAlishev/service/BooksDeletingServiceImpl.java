package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.dao.BooksDAO;
import org.springframework.stereotype.Service;

@Service
public class BooksDeletingServiceImpl implements BooksDeletingService{

    private final BooksDAO booksDAO;

    public BooksDeletingServiceImpl(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @Override
    public void deleteBook(int id) {
        booksDAO.deleteBook(id);
    }
}
