package kz.issak.springCourseAlishev.service;

import kz.issak.springCourseAlishev.dao.PeopleDAO;
import org.springframework.stereotype.Service;

@Service
public class PeopleDeletingServiceImpl implements PeopleDeletingService{
    private final PeopleDAO peopleDAO;

    public PeopleDeletingServiceImpl(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public void deletePerson(int id) {
        peopleDAO.deletePerson(id);
    }
}
