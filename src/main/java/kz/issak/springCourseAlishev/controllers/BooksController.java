package kz.issak.springCourseAlishev.controllers;

import kz.issak.springCourseAlishev.models.Book;
import kz.issak.springCourseAlishev.models.Person;
import kz.issak.springCourseAlishev.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksCreatingService booksCreatingService;
    private final BooksListingService booksListingService;
    private final BooksEditingService booksEditingService;
    private final BooksDeletingService booksDeletingService;

    private final PeopleListingService peopleListingService;

    public BooksController(BooksCreatingService booksCreatingService, BooksListingService booksListingService, BooksEditingService booksEditingService, BooksDeletingService booksDeletingService, PeopleListingService peopleListingService) {
        this.booksCreatingService = booksCreatingService;
        this.booksListingService = booksListingService;
        this.booksEditingService = booksEditingService;
        this.booksDeletingService = booksDeletingService;
        this.peopleListingService = peopleListingService;
    }

    @GetMapping
    public String getBooksList(Model model){ //Получим список книг из DAO и передадим на отображение в представление через модель.
        model.addAttribute("books", booksListingService.getBooksList());
        return "books/ListOfBooks";
    }

    @GetMapping("/new")
//    public String getRegistrationForm(Model model){
//        model.addAttribute("person", new Person()); //Мы должны передавать тот объект которому эта форма нужна. Данное решение можно улучшить: В аргументы метода передаем @ModelAttribute("person") Person person и все можем убрать model.addAtribute("person", new Person()), так как уже @ModelAttribute("person") Person person будет ожидать поля данного объекта, но наш запрос GET там ничего не будет и он просто возьмет и создаст Person по дефолтным значениям. Можно сказать пустым. И также положит в модель и сам модель передаст в view.
//        return "people/NewPerson"; //В файле NewPerson у нас будет форма заполнения данных которые после кнопки submit будут положены в модель с ключом "person" и значением Person (все данные будут положены в объект)
// }
    public String getRegistrationForm(@ModelAttribute("book") Book book){
        return "books/NewBook";
    }

    @PostMapping
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){ //здесь будут лежать данные заполненные в форме выше
        if(bindingResult.hasErrors()) { //Если в bindingResult будут ошибки возникшие при валидации то мы просто перекинем пользователя на страничку обратно для создания человека, но в этой форме уже будут ошибки которые будут показываться с помощью thymeleaf
            return "books/NewBook";
        }
        booksCreatingService.createNewBook(book);
        return "redirect:/books"; //В redirect указывается URI Controller-a который выкинет свой шаблон (GET("/books"))
    }

    @GetMapping("{id}")
    public String getBookPage(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){ //Получим список людей из DAO и передадим на отображение в представление через модель.
        model.addAttribute("book", booksListingService.getBook(id));
        model.addAttribute("people", peopleListingService.getPeopleList());
        model.addAttribute("personWhichIsHaveThisBook", peopleListingService.getPerson(booksListingService.getPersonIdByBookId(id)));
        return "books/PageOfBook";
    }

    @GetMapping("/{id}/edit")
    public String getEditBookPage(Model model, @PathVariable("id") int id){
        model.addAttribute("book", booksListingService.getBook(id)); //к этой модели будем иметь доступ на представлении ниже people/edit
        return "books/EditBook";
    }

    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) { //Если в bindingResult будут ошибки возникшие при валидации то мы просто перекинем пользователя на страничку обратно для создания человека, но в этой форме уже будут ошибки которые будут показываться с помощью thymeleaf
            return "books/EditBook";
        }
        booksEditingService.edit(id, book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int id){
        System.out.println(id);
        booksEditingService.freeBook(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/assign")
    public String assingBook(@PathVariable("id") int id, @RequestParam("id") int personId){
        booksEditingService.setPersonId(id, personId);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksDeletingService.deleteBook(id);
        return "redirect:/books";
    }
}
