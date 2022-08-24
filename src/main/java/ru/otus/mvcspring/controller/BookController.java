package ru.otus.mvcspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.dto.BookDto;
import ru.otus.mvcspring.repositories.BookRepository;

import javax.validation.Valid;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    //@Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") String id, Model model) {
        bookRepository.deleteBookWithAllCommentsById(id);

        return "redirect:/";
    }

    @GetMapping("/book/edit")
    public String editBook(@RequestParam("id") String id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);

        return "bookEdit";
    }

    @PostMapping("/book/edit")
    public String editBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "bookEdit";
        }
        bookRepository.updateTitleById(bookDto.getId(), bookDto.getTitle());

        return "redirect:/";
    }

    @GetMapping("/book/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new BookDto());

        return "bookCreate";
    }

    @PostMapping("/book/create")
    public String createBookSubmit(@ModelAttribute BookDto bookDto, Model model) {
        bookRepository.insert(bookDto.toDomainObject());

        return "redirect:/";
    }
}
