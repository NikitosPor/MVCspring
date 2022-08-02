package ru.otus.mvcspring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mvcspring.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, Long> {

}
