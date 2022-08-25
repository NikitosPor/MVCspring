package ru.otus.mvcspring.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookPagesController {

    @GetMapping("/")
    public String bookList(Model model){

        return "bookList";
    }

}
