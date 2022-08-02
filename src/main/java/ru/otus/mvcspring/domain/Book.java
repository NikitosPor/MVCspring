package ru.otus.mvcspring.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document
public class Book {

    @Id
    private String id;

    private String title;

    private Author author;

    private Genre genre;

    @DBRef
    private List<Comment> comments;

    public Book(String id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, Author author, Genre genre, Comment... comment) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = Arrays.asList(comment);
    }

    public Book(String id, String title, Author author, Genre genre, Comment... comment) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = Arrays.asList(comment);
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
