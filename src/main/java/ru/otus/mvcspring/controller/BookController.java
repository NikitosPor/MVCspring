package ru.otus.mvcspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.repositories.BookRepository;

import java.util.List;

@Controller
public class BookController {


    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String getBookList(Model model) {
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList", bookList);
        return "bookList";
    }
}
