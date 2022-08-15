package ru.otus.mvcspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.dto.BookDto;
import ru.otus.mvcspring.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book/getAll")
    public List<BookDto> getBookList(Model model) {
        return bookRepository.findAll().stream().map(BookDto::fromDomainObject).collect(Collectors.toList());
    }
}
