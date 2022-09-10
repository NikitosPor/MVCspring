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
import ru.otus.mvcspring.changelogs.InitMongoDBDataChangeLog;
import ru.otus.mvcspring.repositories.BookRepository;
import ru.otus.mvcspring.services.CustomUserDetailsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = {BookController.class, InitMongoDBDataChangeLog.class})
class BookControllerTest {

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
        mockMvc.perform(get("/book/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getBookDeleteTest() throws Exception {

        mockMvc.perform(get("/book/delete")
                        .param("id", "A1"))
                .andExpect(status().isOk());
    }
}