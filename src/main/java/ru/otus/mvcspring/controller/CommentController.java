package ru.otus.mvcspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.otus.mvcspring.domain.Comment;
import ru.otus.mvcspring.dto.BookDto;
import ru.otus.mvcspring.dto.CommentDto;
import ru.otus.mvcspring.repositories.CommentRepository;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/getAllByBookId")
    public String getCommentList(@RequestParam("id") String id, Model model) {
        List<Comment> commentList = commentRepository.getCommentsByBookId(id);
        model.addAttribute("commentList", commentList);
        model.addAttribute("bookId", id);

        return "commentList";
    }

    @GetMapping("/delete")
    public String deleteComment(@RequestParam("id") String id, @RequestParam("bookId") String bookId, Model model, RedirectAttributes redirectAttributes) {
        commentRepository.deleteById(id);
        redirectAttributes.addAttribute("id", bookId);

        return "redirect:/comment/getAllByBookId";
    }

    @GetMapping("/create")
    public String createCommentForm(@RequestParam("bookId") String bookId, Model model) {
        model.addAttribute("comment", new CommentDto());
        model.addAttribute("bookId", bookId);

        return "commentCreate";
    }

    @PostMapping("/create")
    public String createCommentSubmit(@RequestParam("bookId") String bookId, @ModelAttribute CommentDto commentDto, Model model, RedirectAttributes redirectAttributes) {
        commentRepository.insertCommentAndLinkWithBookById(bookId, commentDto);
        redirectAttributes.addAttribute("id", bookId);

        return "redirect:/comment/getAllByBookId";
    }

}
