package ru.otus.mvcspring.repositories;

import com.mongodb.DBRef;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.domain.Comment;
import ru.otus.mvcspring.dto.CommentDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public void updateCommentById(String id, String comment) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("comment", comment);
        mongoTemplate.updateFirst(query, update, Comment.class);
    }

    public Comment insertCommentAndLinkWithBookByTitle(String bookTitle, String line) {
        Comment comment = mongoTemplate.save(new Comment(line));
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(bookTitle));
        Update update = new Update().push("comments", new DBRef("comment", new ObjectId(comment.getId())));
        mongoTemplate.updateMulti(query, update, Book.class);

        return comment;
    }

    public void insertCommentAndLinkWithBookById(String bookId, CommentDto commentDto) {
        Comment comment = mongoTemplate.save(new Comment(commentDto.toDomainObject().getComment()));
        Query query = new Query(Criteria.where("_id").is(new ObjectId(bookId)));
        Update update = new Update().push("comments", new DBRef("comment", new ObjectId(comment.getId())));
        mongoTemplate.updateMulti(query, update, Book.class);
    }

    public List<Comment> getCommentsByBookId(String id) {
        List<Comment> comments;
        Query queryBook = new Query(Criteria.where("_id").is(new ObjectId(id)));
        try {
            comments = mongoTemplate.findOne(queryBook, Book.class).getComments();
        } catch (Exception npe) {
            comments = List.of();
        }

        return comments;
    }

}
