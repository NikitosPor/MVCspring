package ru.otus.mvcspring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mvcspring.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

}
