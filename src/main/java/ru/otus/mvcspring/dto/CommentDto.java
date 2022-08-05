package ru.otus.mvcspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.mvcspring.domain.Author;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.domain.Comment;
import ru.otus.mvcspring.domain.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private String id;

    @NotBlank(message = "{comment-field-should-not-be-blank}")
    @Size(min = 2, max = 100, message = "{comment-field-should-has-expected-size}")
    private String comment;

    public Comment toDomainObject() {
        return new Comment(comment);
    }

    public static CommentDto fromDomainObject(Comment comment) {
        return new CommentDto(comment.getId(), comment.getComment());
    }
}
