package kz.issak.springCourseAlishev.rowMapper;

import kz.issak.springCourseAlishev.models.Book;
import kz.issak.springCourseAlishev.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getInt("id"));
        book.setPersonId(resultSet.getInt("person_id"));
        book.setName(resultSet.getString("name"));
        book.setAuthorName(resultSet.getString("authorName"));
        book.setDateOfPublish(resultSet.getInt("dateOfPublish"));

        return book;
    }
}
