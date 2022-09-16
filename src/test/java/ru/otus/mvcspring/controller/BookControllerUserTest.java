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
import ru.otus.mvcspring.security.SecurityConfiguration;
import ru.otus.mvcspring.services.CustomUserDetailsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = {BookController.class, SecurityConfiguration.class})
class BookControllerUserTest {

    public static final String BOOK_ID = "A1";

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private MongoTemplate mongoTemplate;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "TEST", authorities = {"ROLE_USER"})
    @Test
    void getBookListTest() throws Exception {
        mockMvc.perform(get("/book/getAll"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "TEST", authorities = {"ROLE_USER"})
    @Test
    void getBookDeleteTest() throws Exception {
        mockMvc.perform(get("/book/delete")
                        .param("id", BOOK_ID))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username = "TEST", authorities = {"ROLE_USER"})
    @Test
    void editBookTest() throws Exception {
        mockMvc.perform(get("/book/edit")
                        .param("id", BOOK_ID))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username = "TEST", authorities = {"ROLE_USER"})
    @Test
    void createBookFormTest() throws Exception {
        mockMvc.perform(get("/book/create")
                        .param("id", BOOK_ID))
                .andExpect(status().is4xxClientError());
    }
}