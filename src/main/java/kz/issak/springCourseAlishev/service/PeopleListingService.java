package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;
public interface PeopleListingService {
    public List<Person> getPeopleList();

    public Person getPerson(int id);
}
