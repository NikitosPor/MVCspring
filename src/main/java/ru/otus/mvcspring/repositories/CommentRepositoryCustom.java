package ru.otus.mvcspring.repositories;
import ru.otus.mvcspring.domain.Comment;
import ru.otus.mvcspring.dto.CommentDto;

import java.util.List;

public interface CommentRepositoryCustom {

    void updateCommentById(String id, String title);

    Comment insertCommentAndLinkWithBookByTitle(String title, String comment);

    void insertCommentAndLinkWithBookById(String bookId, CommentDto commentDto);

    List<Comment> getCommentsByBookId(String id);

}
