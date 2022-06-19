package kz.issak.springCourseAlishev.dao;

import kz.issak.springCourseAlishev.models.Book;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BooksDAO {
    private List<Book> books;
    private static int BOOK_COUNTER;

    {
        books = new ArrayList<>();

        books.add(new Book(++BOOK_COUNTER, "Baglan Issak Daurenbekuly", "Baglan", 1996));
        books.add(new Book(++BOOK_COUNTER, "Symbat Issak Kassymkyzy", "Symbat",1996));
        books.add(new Book(++BOOK_COUNTER, "Amir Issak Baglanuly", "Amir",2020));
        books.add(new Book(++BOOK_COUNTER, "Hadisha Issak Baglankyzy", "Amir", 2022));
    }

    public List<Book> getBooksList(){
        return books;
    }

    public Book getBook(int id){
        return books.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }

    public void addBook(Book book){
        book.setId(++BOOK_COUNTER);
        books.add(book);
    }

    public void editBook(int id, Book updatedBook){
        Book bookToBeEdited = getBook(id);
        bookToBeEdited.setAuthorName(updatedBook.getAuthorName());
        bookToBeEdited.setName(updatedBook.getName());
        bookToBeEdited.setDateOfPublish(updatedBook.getDateOfPublish());
    }

    public void deleteBook(int id){
        books.removeIf(p -> p.getId() == id);
    }
}
