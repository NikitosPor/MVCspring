package ru.otus.mvcspring.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.mvcspring.domain.*;
import ru.otus.mvcspring.repositories.*;


@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Author author1;
    private Author author2;
    private Author author3;
    private Author author4;
    private Author author5;

    private Genre genre1;
    private Genre genre2;
    private Genre genre3;

    private Comment comment1;
    private Comment comment2;
    private Comment comment3;
    private Comment comment4;
    private Comment comment5;
    private Comment comment6;
    private Comment comment7;


    private User user1;
    private User user2;
    private User user3;


    @ChangeSet(order = "000", id = "dropDB", author = "np", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthor", author = "np", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        author1 = repository.insert(new Author("A1", "Leo Tolstoy"));
        author2 = repository.insert(new Author("A2", "Alex Pushkin"));
        author3 = repository.insert(new Author("A3", "Michael Sholokhov"));
        author4 = repository.insert(new Author("A4", "Arthur Conan Doyle"));
        author5 = repository.insert(new Author("A5", "Nikolay Gogol"));
    }

    @ChangeSet(order = "002", id = "initGenre", author = "np", runAlways = true)
    public void initGenres(GenreRepository repository) {
        genre1 = repository.save(new Genre("G1", "Tale"));
        genre2 = repository.save(new Genre("G2", "Drama"));
        genre3 = repository.save(new Genre("G3", "Detective"));
    }

    @ChangeSet(order = "003", id = "initComment", author = "np", runAlways = true)
    public void initComments(CommentRepository repository) {
        comment1 = repository.save(new Comment("C1", "Good example of traditional Russian culture"));
        comment2 = repository.save(new Comment("C2","One of the most epic drama in the world"));
        comment3 = repository.save(new Comment("C3","Leo Tolstoy wrote it when he was old boy"));
        comment4 = repository.save(new Comment("C4","Famous literature composition"));
        comment5 = repository.save(new Comment("C5","One of the most epic drama in the world"));
        comment6 = repository.save(new Comment("C6","Alex Pushkin wrote it when he was young boy"));
        comment7 = repository.save(new Comment("C7","Russian pupils learn it in school"));
    }


    @ChangeSet(order = "004", id = "initBooks", author = "np", runAlways = true)
    public void initBooks(BookRepository repository) {
        repository.save(new Book("B1","War and peace", author1, genre2, comment1, comment2, comment3));
        repository.save(new Book("B2","Eugene Onegin", author2, genre2, comment4, comment5, comment6, comment7));
        repository.save(new Book("B3","Kazaks", author1, genre1));
        repository.save(new Book("B4","Silent Don", author3, genre2));
        repository.save(new Book("B5","Sherlock Holmes", author4, genre3));
        repository.save(new Book("B6","Taras Bulba", author5, genre2));
    }

    @ChangeSet(order = "005", id = "initUser", author = "np", runAlways = true)
    public void initUsers(UserRepository repository) {
        user1 = repository.save(new User("admin", "admin"));
        user2 = repository.save(new User("user1", "user1"));
        user3 = repository.save(new User("user2", "user2"));
    }
}
