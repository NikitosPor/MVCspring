package ru.otus.mvcspring.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mvcspring.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {

    void deleteByIdIn(List<String> ids);

}
