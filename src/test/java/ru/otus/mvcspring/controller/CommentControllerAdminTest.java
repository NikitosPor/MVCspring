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
import ru.otus.mvcspring.repositories.BookRepository;
import ru.otus.mvcspring.repositories.CommentRepository;
import ru.otus.mvcspring.security.SecurityConfiguration;
import ru.otus.mvcspring.services.CustomUserDetailsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
@ContextConfiguration(classes = {CommentController.class, SecurityConfiguration.class})
class CommentControllerAdminTest {


    public static final String BOOK_ID = "A1";
    public static final String COMMENT_ID = "C1";

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

    //@BeforeEach

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void getBookListTest() throws Exception {
        mockMvc.perform(get("/comment/getAllByBookId").param("id", BOOK_ID))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void deleteCommentTest() throws Exception {
//        bookRepository.insert(BOOK);
        mockMvc.perform(get("/comment/delete").param("id", COMMENT_ID).param("bookId", BOOK_ID))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void makeCommentCreationFormTest() throws Exception {
        mockMvc.perform(get("/comment/create").param("bookId",BOOK_ID))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void makeCommentEditFormTest() throws Exception {
        mockMvc.perform(get("/comment/edit").param("bookId",BOOK_ID))
                .andExpect(status().is4xxClientError());
    }

}