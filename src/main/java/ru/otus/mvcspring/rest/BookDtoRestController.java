package ru.otus.mvcspring.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mvcspring.dto.BookDto;
import ru.otus.mvcspring.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookDtoRestController {

    private final BookRepository bookRepository;

    public BookDtoRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book")
    public List<BookDto> getBookList() {
        return bookRepository.findAll().stream().map(BookDto::fromDomainObject).collect(Collectors.toList());
    }

}
