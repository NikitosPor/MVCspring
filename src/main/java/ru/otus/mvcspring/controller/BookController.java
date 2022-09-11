package ru.otus.mvcspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.dto.BookDto;
import ru.otus.mvcspring.repositories.BookRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book/getAll")
    public String getBookList(Model model) {
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList", bookList);

        return "bookList";
    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") String id, Model model) {
        bookRepository.deleteBookWithAllCommentsById(id);

        return "redirect:/book/getAll";
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

        return "redirect:/book/getAll";
    }

    @GetMapping("/book/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new BookDto());

        return "bookCreate";
    }

    @PostMapping("/book/create")
    public String createBookSubmit(@ModelAttribute BookDto bookDto, Model model) {
        bookRepository.insert(bookDto.toDomainObject());

        return "redirect:/book/getAll";
    }
}
