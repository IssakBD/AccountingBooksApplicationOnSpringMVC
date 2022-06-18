package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.dao.PeopleDAO;
import kz.issak.springCourseAlishev.models.Person;
import org.springframework.stereotype.Service;

@Service
public class PeopleCreatingServiceImpl implements PeopleCreatingService{

    private final PeopleDAO peopleDAO;

    public PeopleCreatingServiceImpl(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public void createNewPerson(Person person) {
        peopleDAO.addPerson(person);
    }
}
