package ru.otus.mvcspring.repositories;
import ru.otus.mvcspring.domain.Comment;

public interface CommentRepositoryCustom {

    void updateCommentById(String id, String title);

    Comment insertCommentAndLinkWithBookByTitle(String title, String comment);

}
