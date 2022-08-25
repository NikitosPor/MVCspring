package ru.otus.mvcspring.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentPagesController {

    @GetMapping("/book-comment")
    public String commentList(@RequestParam("bookId") String bookId, Model model) {
        model.addAttribute("bookId", bookId);

        return "commentList";
    }

}
