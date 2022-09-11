package ru.otus.mvcspring.repositories;

public interface BookRepositoryCustom {

    void updateTitleByTitle(String oldTitle, String title);

    void updateTitleById(String id, String title);

    void deleteBookWithAllCommentsByTitle(String id);

    void deleteBookWithAllCommentsById(String id);

}
