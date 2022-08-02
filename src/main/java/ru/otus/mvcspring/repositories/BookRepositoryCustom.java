package ru.otus.mvcspring.repositories;

public interface BookRepositoryCustom {

    void updateTitleByTitle(String id, String title);

    void deleteBookWithAllCommentsByTitle(String id);

}
