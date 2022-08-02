package ru.otus.mvcspring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mvcspring.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, Long> {

}
