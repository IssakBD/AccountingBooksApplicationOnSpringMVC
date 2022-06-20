package kz.issak.springCourseAlishev.dao;

import kz.issak.springCourseAlishev.models.Person;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PeopleDAO {

    //Выполнение на arrayList-е вместо ДБ
//    private List<Person> people;
//    private static int PEOPLE_COUNTER;
//
//    {
//        people = new ArrayList<>();
//
//        people.add(new Person(++PEOPLE_COUNTER, "Baglan Issak Daurenbekuly", "24.09.1996"));
//        people.add(new Person(++PEOPLE_COUNTER, "Symbat Issak Kassymkyzy", "25.06.1996"));
//        people.add(new Person(++PEOPLE_COUNTER, "Amir Issak Baglanuly", "21.07.2020"));
//        people.add(new Person(++PEOPLE_COUNTER, "Hadisha Issak Baglankyzy", "20.01.2022"));
//    }

    //  С помощью JDBC API {
    private static final String URL = "jdbc:postgresql://localhost:5432/project1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection; //Соединение с БД

    static {
        try {
            Class.forName("org.postgresql.Driver"); //С помощью рефлексии подгружаем класс с нашим JDBC driver, в последних версиях не требуется это делать
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //Подключаемся к БД с помощью драйвера
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //}
    public List<Person> getPeopleList(){
        //Выполнение на arrayList-е вместо ДБ
        //return people;


        //С помощью JDBC API
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(); //на нашем соединении создаем запрос в БД
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL); //Выполняет запрос на нашей БД. ResultSet - объект который инкапсулирует результат запроса к БД.
            while (resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id")); //Извлекаем и сетим данные в person из resultSet
                person.setFullName(resultSet.getString("fullName"));
                person.setDateOfBirth(resultSet.getString("dateOfBirth"));

                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public Person getPerson(int id){
        //Выполнение на arrayList-е вместо ДБ
        //return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);

        //С помощью JDBC API
        Person person = new Person();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person.setId(resultSet.getInt("id"));
            person.setFullName(resultSet.getString("fullName"));
            person.setDateOfBirth(resultSet.getString("dateOfBirth"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    public void addPerson(Person person){
        //Выполнение на arrayList-е вместо ДБ
//        person.setId(++PEOPLE_COUNTER);
//        people.add(person);

        //Изначально делал на statement вместо preparedStatement. Statement медленнее и опаснее чем PreparedStatement.
        //С помощью JDBC API
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person(fullName, dateOfBirth) VALUES (?, ?)");
            preparedStatement.setString(1, person.getFullName());
            preparedStatement.setString(2, person.getDateOfBirth());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPerson(int id, Person updatedPerson){
        //Выполнение на arrayList-е вместо ДБ
//        Person personToBeEdited = getPerson(id);
//        personToBeEdited.setFullName(updatedPerson.getFullName());
//        personToBeEdited.setDateOfBirth(updatedPerson.getDateOfBirth());

        //С помощью JDBC API
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET fullName = ?, dateOfBirth = ? WHERE id = ?");
            preparedStatement.setString(1, updatedPerson.getFullName());
            preparedStatement.setString(2, updatedPerson.getDateOfBirth());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePerson(int id){
        //Выполнение на arrayList-е вместо ДБ
        //people.removeIf(p -> p.getId() == id);

        //С помощью JDBC API
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
