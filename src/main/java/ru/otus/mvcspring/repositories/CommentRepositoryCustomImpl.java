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

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateCommentById(String id, String comment) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("comment", comment);
        mongoTemplate.updateFirst(query, update, Comment.class);
    }

    @Override
    public Comment insertCommentAndLinkWithBookByTitle(String bookTitle, String line) {

        Comment comment = mongoTemplate.save(new Comment(line));
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(bookTitle));
        Update update = new Update().push("comments", new DBRef("comment", new ObjectId(comment.getId())));
        mongoTemplate.updateMulti(query, update, Book.class);

        return comment;
    }

}
