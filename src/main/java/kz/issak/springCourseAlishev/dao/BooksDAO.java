package kz.issak.springCourseAlishev.dao;

import kz.issak.springCourseAlishev.models.Book;
import kz.issak.springCourseAlishev.models.Person;
import kz.issak.springCourseAlishev.rowMapper.BookMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class BooksDAO {
    //Выполнение на arrayList-е вместо ДБ
//    private List<Book> books;
//    private static int BOOK_COUNTER;

//    {
//        books = new ArrayList<>();
//
//        books.add(new Book(++BOOK_COUNTER, "Baglan Issak Daurenbekuly", "Baglan", 1996));
//        books.add(new Book(++BOOK_COUNTER, "Symbat Issak Kassymkyzy", "Symbat",1996));
//        books.add(new Book(++BOOK_COUNTER, "Amir Issak Baglanuly", "Amir",2020));
//        books.add(new Book(++BOOK_COUNTER, "Hadisha Issak Baglankyzy", "Amir", 2022));
//    }

    //С помощью JDBC API {
//    private static final String URL = "jdbc:postgresql://localhost:5432/project1";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "postgres";
//
//    private static Connection connection; //Соединение с БД
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver"); //С помощью рефлексии подгружаем класс с нашим JDBC driver, в последних версиях не требуется это делать
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //Подключаемся к БД с помощью драйвера
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//}

//  С помощью JDBC Template
    private final JdbcTemplate jdbcTemplate;

    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksList(){
        //Выполнение на arrayList-е вместо ДБ
        //return books;

        //С помощью JDBC API
//        List<Book> books = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement(); //на нашем соединении создаем запрос в БД
//            String SQL = "SELECT * FROM Book";
//            ResultSet resultSet = statement.executeQuery(SQL); //Выполняет запрос на нашей БД. ResultSet - объект который инкапсулирует результат запроса к БД.
//            while (resultSet.next()){
//                Book book = new Book();
//
//                book.setId(resultSet.getInt("id")); //Извлекаем и сетим данные в person из resultSet
//                book.setName(resultSet.getString("name"));
//                book.setAuthorName(resultSet.getString("authorName"));
//                book.setDateOfPublish(resultSet.getInt("dateOfPublish"));
//
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return books;



        //  С помощью JDBC Template
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book getBook(int id){
        //Выполнение на arrayList-е вместо ДБ
        //return books.stream().filter(book -> book.getId() == id).findAny().orElse(null);

        //С помощью JDBC API
//        Book book = new Book();
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("SELECT * FROM Book WHERE id = ?");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            book.setId(resultSet.getInt("id"));
//            book.setName(resultSet.getString("name"));
//            book.setAuthorName(resultSet.getString("authorName"));
//            book.setDateOfPublish(resultSet.getInt("dateOfPublish"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return book;



        //  С помощью JDBC Template
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new BookMapper(), new Object[]{id}).stream().findAny().orElse(null);
    }

    public void addBook(Book book){
        //Выполнение на arrayList-е вместо ДБ
//        book.setId(++BOOK_COUNTER);
//        books.add(book);

        //Изначально делал на statement вместо preparedStatement. Statement медленнее и опаснее чем PreparedStatement.
        //С помощью JDBC API
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("INSERT INTO Book(name, authorName, dateOfPublish) VALUES (?, ?, ?)");
//            preparedStatement.setString(1, book.getName());
//            preparedStatement.setString(2, book.getAuthorName());
//            preparedStatement.setInt(3, book.getDateOfPublish());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }



        //  С помощью JDBC Template
        jdbcTemplate.update("INSERT INTO Book(name, authorName, dateOfPublish) VALUES (?, ?, ?)", book.getName(), book.getAuthorName(), book.getDateOfPublish());
    }

    public void editBook(int id, Book updatedBook){
        //Выполнение на arrayList-е вместо ДБ
//        Book bookToBeEdited = getBook(id);
//        bookToBeEdited.setAuthorName(updatedBook.getAuthorName());
//        bookToBeEdited.setName(updatedBook.getName());
//        bookToBeEdited.setDateOfPublish(updatedBook.getDateOfPublish());

        //С помощью JDBC API
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE Book SET name = ?, authorName = ?, dateOfPublish = ? WHERE id = ?");
//            preparedStatement.setString(1, updatedBook.getName());
//            preparedStatement.setString(2, updatedBook.getAuthorName());
//            preparedStatement.setInt(3, updatedBook.getDateOfPublish());
//            preparedStatement.setInt(4, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }



        //  С помощью JDBC Template
        jdbcTemplate.update("UPDATE Book SET name = ?, authorName = ?, dateOfPublish = ? WHERE id = ?", updatedBook.getName(), updatedBook.getAuthorName(), updatedBook.getDateOfPublish(), id);

    }

    public void deleteBook(int id){
        //Выполнение на arrayList-е вместо ДБ
//        books.removeIf(p -> p.getId() == id);

        //С помощью JDBC API
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Book WHERE id = ?");
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }



        //  С помощью JDBC Template
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }

    public void setPersonId(int id, int personId){
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE id = ?", personId, id);
    }


    public List<Book> getBookListByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new BookMapper(), new Object[]{id});
    }

    public int getPersonIdByBookId(int id){
        Book book = getBook(id);
        return book.getPersonId();
    }

    public void freeBook(int id){
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE id = ?", null, id);
    }
}
