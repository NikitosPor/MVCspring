package ru.otus.mvcspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.mvcspring.domain.Author;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.domain.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;

    @NotBlank(message = "{title-field-should-not-be-blank}")
    @Size(min = 2, max = 25, message = "{title-field-should-has-expected-size}")
    private String title;

    private String author;

    private String genre;

    public Book toDomainObject() {
        return new Book(title, new Author(author), new Genre(genre));
    }

    public static BookDto fromDomainObject(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getTitle());
    }
}
