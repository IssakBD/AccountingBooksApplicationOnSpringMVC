package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.dao.BooksDAO;
import kz.issak.springCourseAlishev.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksListingServiceImpl implements BooksListingService{

    private final BooksDAO booksDAO;

    public BooksListingServiceImpl(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @Override
    public List<Book> getBooksList() {
        return booksDAO.getBooksList();
    }

    @Override
    public Book getBook(int id) {
        return booksDAO.getBook(id);
    }

    @Override
    public List<Book> getBookListByPersonId(int id) {
        return booksDAO.getBookListByPersonId(id);
    }

    @Override
    public int getPersonIdByBookId(int id) {
        return booksDAO.getPersonIdByBookId(id);
    }
}
