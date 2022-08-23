package ru.otus.mvcspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.otus.mvcspring.domain.Comment;
import ru.otus.mvcspring.dto.BookDto;
import ru.otus.mvcspring.dto.CommentDto;
import ru.otus.mvcspring.repositories.CommentRepository;

import java.util.List;

@Controller
public class CommentController {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

//    @GetMapping("/comment")
//    public String getCommentList(@RequestParam("id") String id, Model model) {
//        List<Comment> commentList = commentRepository.getCommentsByBookId(id);
//        model.addAttribute("commentList", commentList);
//        model.addAttribute("bookId", id);
//
//        return "commentList";
//    }

    @GetMapping("/comment/delete")
    public String deleteComment(@RequestParam("id") String id, @RequestParam("bookId") String bookId, Model model, RedirectAttributes redirectAttributes) {
        commentRepository.deleteById(id);
        redirectAttributes.addAttribute("id", bookId);

        return "redirect:/comment";
    }

    @GetMapping("/comment/create")
    public String makeCommentCreationForm(@RequestParam("bookId") String bookId, Model model) {
        model.addAttribute("comment", new CommentDto());
        model.addAttribute("bookId", bookId);

        return "commentCreate";
    }

    @PostMapping("/comment/create")
    public String makeCommentSubmit(@RequestParam("bookId") String bookId, @ModelAttribute CommentDto commentDto, Model model, RedirectAttributes redirectAttributes) {
        commentRepository.insertCommentAndLinkWithBookById(bookId, commentDto);
        redirectAttributes.addAttribute("id", bookId);

        return "redirect:/comment";
    }

    @GetMapping("/comment/edit")
    public String makeCommentEditForm(@RequestParam("id") String id, @RequestParam("bookId") String bookId, Model model) {
        Comment comment = commentRepository.getCommentById(id);
        model.addAttribute("comment", comment);
        model.addAttribute("bookId", bookId);
        model.addAttribute("commentId", id);

        return "commentEdit";
    }

    @PostMapping("/comment/edit")
    public String makeCommentEditSubmit(@RequestParam("bookId") String bookId, @ModelAttribute CommentDto commentDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        commentRepository.updateCommentById(commentDto.getId(), commentDto.getComment());
        redirectAttributes.addAttribute("id", bookId);

        return "redirect:/comment";
    }

}
