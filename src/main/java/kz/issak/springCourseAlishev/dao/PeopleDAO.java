package kz.issak.springCourseAlishev.dao;

import kz.issak.springCourseAlishev.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class PeopleDAO {
    private List<Person> people;
    private static int PEOPLE_COUNTER;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNTER, "Baglan Issak Daurenbekuly", "24.09.1996"));
        people.add(new Person(++PEOPLE_COUNTER, "Symbat Issak Kassymkyzy", "25.06.1996"));
        people.add(new Person(++PEOPLE_COUNTER, "Amir Issak Baglanuly", "21.07.2020"));
        people.add(new Person(++PEOPLE_COUNTER, "Hadisha Issak Baglankyzy", "20.01.2022"));
    }

    public List<Person> getPeopleList(){
        return people;
    }

    public Person getPerson(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void addPerson(Person person){
        person.setId(++PEOPLE_COUNTER);
        people.add(person);
    }

    public void editPerson(int id, Person updatedPerson){
        Person personToBeEdited = getPerson(id);
        personToBeEdited.setFullName(updatedPerson.getFullName());
        personToBeEdited.setDateOfBirth(updatedPerson.getDateOfBirth());
    }

    public void deletePerson(int id){
        people.removeIf(p -> p.getId() == id);
    }


}
