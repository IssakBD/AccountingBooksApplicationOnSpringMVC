package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.dao.PeopleDAO;
import kz.issak.springCourseAlishev.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleListingServiceImpl implements PeopleListingService{

    private final PeopleDAO peopleDAO;

    public PeopleListingServiceImpl(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public List<Person> getPeopleList() {
        return peopleDAO.getPeopleList();
    }

    @Override
    public Person getPerson(int id) {
        return peopleDAO.getPerson(id);
    }

    @Override
    public Optional<Person> getPersonByFullName(String fullName) {
        return peopleDAO.getPersonByFullName(fullName);
    }

}
