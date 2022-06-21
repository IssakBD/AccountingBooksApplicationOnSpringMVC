package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PeopleListingService {
    public List<Person> getPeopleList();

    public Person getPerson(int id);

    public Optional<Person> getPersonByFullName(String fullName);
}
