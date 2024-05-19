package app.controller;

import app.entities.Comment;
import app.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/all")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/all/{id}")
    public List<Comment> getAllCommentsForProgram(@PathVariable("id") int id) {
        return commentService.getAllCommentsForProgram(id);
    }

    @PostMapping("/create")
    public Comment createComment(
            @RequestBody Comment comment,
            @RequestParam int fitnessProgramId,
            @RequestParam String user) {
        return commentService.createComment(comment, fitnessProgramId, user);
    }
}
