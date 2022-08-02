package ru.otus.mvcspring.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.mvcspring.domain.Author;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.domain.Comment;
import ru.otus.mvcspring.domain.Genre;
import ru.otus.mvcspring.repositories.AuthorRepository;
import ru.otus.mvcspring.repositories.BookRepository;
import ru.otus.mvcspring.repositories.CommentRepository;
import ru.otus.mvcspring.repositories.GenreRepository;


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


    @ChangeSet(order = "000", id = "dropDB", author = "np", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthor", author = "np", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        author1 = repository.insert(new Author("Leo Tolstoy"));
        author2 = repository.insert(new Author("Alex Pushkin"));
        author3 = repository.insert(new Author("Michael Sholokhov"));
        author4 = repository.insert(new Author("Arthur Conan Doyle"));
        author5 = repository.insert(new Author("Nikolay Gogol"));
    }

    @ChangeSet(order = "002", id = "initGenre", author = "np", runAlways = true)
    public void initGenres(GenreRepository repository) {
        genre1 = repository.save(new Genre("Tale"));
        genre2 = repository.save(new Genre("Drama"));
        genre3 = repository.save(new Genre("Detective"));
    }

    @ChangeSet(order = "003", id = "initComment", author = "np", runAlways = true)
    public void initComments(CommentRepository repository) {
        comment1 = repository.save(new Comment("Good example of traditional Russian culture"));
        comment2 = repository.save(new Comment("One of the most epic drama in the world"));
        comment3 = repository.save(new Comment("Leo Tolstoy wrote it when he was old boy"));
        comment4 = repository.save(new Comment("Famous literature composition"));
        comment5 = repository.save(new Comment("One of the most epic drama in the world"));
        comment6 = repository.save(new Comment("Alex Pushkin wrote it when he was young boy"));
        comment7 = repository.save(new Comment("Russian pupils learn it in school"));
    }


    @ChangeSet(order = "004", id = "initBooks", author = "np", runAlways = true)
    public void initBooks(BookRepository repository) {
        repository.save(new Book("War and peace", author1, genre2, comment1, comment2, comment3));
        repository.save(new Book("Eugene Onegin", author2, genre2, comment4, comment5, comment6, comment7));
        repository.save(new Book("Kazaks", author1, genre1));
        repository.save(new Book("Silent Don", author3, genre2));
        repository.save(new Book("Sherlock Holmes", author4, genre3));
        repository.save(new Book("Taras Bulba", author5, genre2));
    }
}
