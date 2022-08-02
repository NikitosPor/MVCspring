package ru.otus.mvcspring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;
    private final CommentRepository commentRepository;

    @Override
    public void updateTitleByTitle(String oldTitle, String newTitle) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(oldTitle));
        Update update = new Update().set("title", newTitle);
        mongoTemplate.updateFirst(query, update, Book.class);
    }


    @Override
    public void deleteBookWithAllCommentsByTitle(String bookTitle) {
        Query queryBook = new Query(Criteria.where("title").is(bookTitle));
        try {
            List<String> commentsId = mongoTemplate.findOne(queryBook, Book.class).getComments().stream().map(Comment::getId).collect(Collectors.toList());
            commentRepository.deleteByIdIn(commentsId);
        } catch (Exception npe) {
            //нет комментов
        } finally {
            mongoTemplate.findAllAndRemove(queryBook, Book.class);
        }

    }

}
