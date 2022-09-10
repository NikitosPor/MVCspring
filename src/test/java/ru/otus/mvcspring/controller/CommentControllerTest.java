package ru.otus.mvcspring.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.mvcspring.domain.Author;
import ru.otus.mvcspring.domain.Book;
import ru.otus.mvcspring.domain.Comment;
import ru.otus.mvcspring.domain.Genre;
import ru.otus.mvcspring.repositories.BookRepository;
import ru.otus.mvcspring.repositories.CommentRepository;
import ru.otus.mvcspring.services.CustomUserDetailsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
@ContextConfiguration(classes = CommentController.class)
class CommentControllerTest {


    public static final String BOOK_ID = "A1";
    public static final Comment COMMENT_1 = new Comment("C1", "CCC");
    public static final Comment COMMENT_2 = new Comment("C2", "CCC");
    public static final List<Comment> LIST_OF_COMMENTS = List.of(COMMENT_1, COMMENT_2);
    public static final Genre GENRE = new Genre("GGG");
    public static final Author AUTHOR = new Author("AAA");
    public static final Book BOOK = new Book(BOOK_ID, "TTT", AUTHOR, GENRE, COMMENT_1);

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private MongoTemplate mongoTemplate;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )

    @Test
    void getBookListTest() throws Exception {
        bookRepository.insert(BOOK);

        given(commentRepository.getCommentsByBookId(BOOK_ID)).willReturn(LIST_OF_COMMENTS);

        mockMvc.perform(get("/comment/getAllByBookId").param("bookId", BOOK_ID))
                .andExpect(status().isOk());
    }
}