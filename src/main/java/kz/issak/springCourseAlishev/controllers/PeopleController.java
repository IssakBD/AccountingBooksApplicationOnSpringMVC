package kz.issak.springCourseAlishev.controllers;

import kz.issak.springCourseAlishev.models.Person;
import kz.issak.springCourseAlishev.service.PeopleCreatingService;
import kz.issak.springCourseAlishev.service.PeopleDeletingService;
import kz.issak.springCourseAlishev.service.PeopleEditingService;
import kz.issak.springCourseAlishev.service.PeopleListingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleCreatingService peopleCreatingService;
    private final PeopleListingService peopleListingService;
    private final PeopleEditingService peopleEditingService;

    private final PeopleDeletingService peopleDeletingService;

    public PeopleController(PeopleCreatingService peopleCreatingService, PeopleListingService peopleListingService, PeopleEditingService peopleEditingService, PeopleDeletingService peopleDeletingService) {
        this.peopleCreatingService = peopleCreatingService;
        this.peopleListingService = peopleListingService;
        this.peopleEditingService = peopleEditingService;
        this.peopleDeletingService = peopleDeletingService;
    }

    @GetMapping()
    public String getPeopleList(Model model){ //Получим список людей из DAO и передадим на отображение в представление через модель.
        model.addAttribute("people", peopleListingService.getPeopleList());
        return "people/ListOfPeople";
    }

    @GetMapping("/new")
//    public String getRegistrationForm(Model model){
//        model.addAttribute("person", new Person()); //Мы должны передавать тот объект которому эта форма нужна. Данное решение можно улучшить: В аргументы метода передаем @ModelAttribute("person") Person person и все можем убрать model.addAtribute("person", new Person()), так как уже @ModelAttribute("person") Person person будет ожидать поля данного объекта, но наш запрос GET там ничего не будет и он просто возьмет и создаст Person по дефолтным значениям. Можно сказать пустым. И также положит в модель и сам модель передаст в view.
//        return "people/NewPerson"; //В файле NewPerson у нас будет форма заполнения данных которые после кнопки submit будут положены в модель с ключом "person" и значением Person (все данные будут положены в объект)
// }
    public String getRegistrationForm(@ModelAttribute("person") Person person){
        return "people/NewPerson";
    }

    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){ //В Person person здесь будут лежать данные заполненные в форме выше
        if(bindingResult.hasErrors()) { //Если в bindingResult будут ошибки возникшие при валидации то мы просто перекинем пользователя на страничку обратно для создания человека, но в этой форме уже будут ошибки которые будут показываться с помощью thymeleaf
            System.out.println("BindingResult has ERROR");
            return "people/NewPerson";
        }
        System.out.println("binding result has not error");
        peopleCreatingService.createNewPerson(person);
        return "redirect:/people"; //В redirect указывается URI Controller-a который выкинет свой шаблон
        //@Valid ставится перед объектом которая должна валидироваться, и после нее обязательно нужно поставить BindingResult объект в который попадут сообщения при валидации который прописаны в самом объекте
    }

    @GetMapping("{id}")
    public String getPersonPage(@PathVariable("id") int id, Model model){ //Получим список людей из DAO и передадим на отображение в представление через модель.
        model.addAttribute("person", peopleListingService.getPerson(id));
        return "people/PageOfPerson";
    }

    @GetMapping("/{id}/edit")
    public String getEditPersonPage(Model model, @PathVariable("id") int id){
        model.addAttribute("person", peopleListingService.getPerson(id)); //к этой модели будем иметь доступ на представлении ниже people/edit
        return "people/EditPerson";
    }

    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "people/EditPerson";
        }
        peopleEditingService.edit(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleDeletingService.deletePerson(id);
        return "redirect:/people";
    }


}
