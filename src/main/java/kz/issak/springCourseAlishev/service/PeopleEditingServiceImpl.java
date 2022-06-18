package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.dao.PeopleDAO;
import kz.issak.springCourseAlishev.models.Person;
import org.springframework.stereotype.Service;

@Service
public class PeopleEditingServiceImpl implements PeopleEditingService{
    private final PeopleDAO peopleDAO;

    public PeopleEditingServiceImpl(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public void edit(int id, Person person) {
        peopleDAO.editPerson(id, person);
    }
}
