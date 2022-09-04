package ru.otus.mvcspring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.mvcspring.domain.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findUserByName(String name);
}

