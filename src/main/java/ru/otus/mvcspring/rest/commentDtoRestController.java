package ru.otus.mvcspring.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mvcspring.domain.Comment;
import ru.otus.mvcspring.dto.BookDto;
import ru.otus.mvcspring.dto.CommentDto;
import ru.otus.mvcspring.repositories.BookRepository;
import ru.otus.mvcspring.repositories.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class commentDtoRestController {

    private final CommentRepository commentRepository;

    public commentDtoRestController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comment")
    public List<CommentDto> getCommentList(@RequestParam("id") String id) {
        List<Comment> commentList = commentRepository.getCommentsByBookId(id);
        return commentRepository.findAll().stream().map(CommentDto::fromDomainObject).collect(Collectors.toList());
    }

}
