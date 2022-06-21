package kz.issak.springCourseAlishev.rowMapper;

import kz.issak.springCourseAlishev.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setFullName(resultSet.getString("fullName"));
        person.setDateOfBirth(resultSet.getString("dateOfBirth"));

        return person;
    }
}
