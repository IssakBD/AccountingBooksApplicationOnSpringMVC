package kz.issak.springCourseAlishev.util;

import kz.issak.springCourseAlishev.models.Person;
import kz.issak.springCourseAlishev.service.PeopleListingService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator { //Нужен для того чтобы ошибки связанные с валидацией на БД выводить на экран клиента понятным образом, а не в виде ошибки

    private final PeopleListingService peopleListingService;

    public PersonValidator(PeopleListingService peopleListingService) {
        this.peopleListingService = peopleListingService;
    }

    @Override
    public boolean supports(Class<?> aClass) { //указываем какому классу предназначен данный валидатор
        return Person.class.equals(aClass); //Если класс который передается в качестве аргумента равняется Person то мы сможем использовать валидатор
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(peopleListingService.getPersonByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName","", "This full name is already exist"); //В errors ложим сообщение в виде ошибки.
        }
    }
}
