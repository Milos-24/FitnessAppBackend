package app.service;

import app.entities.Comment;
import app.entities.FitnessProgram;
import app.entities.User;
import app.repositories.CommentRepository;
import app.repositories.FitnessProgramRepository;
import app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final FitnessProgramRepository fitnessProgramRepository;
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllCommentsForProgram(int id) { return commentRepository.findAllByFitnessProgramId(id);}

    public Comment createComment(Comment comment, int fitnessProgramId, String username) {
        FitnessProgram fitnessProgram = fitnessProgramRepository.findById(fitnessProgramId)
                .orElseThrow(() -> new IllegalArgumentException("Fitness program not found with id: " + fitnessProgramId));

        User user = userRepository.findByUsername(username);

        Comment newComment = new Comment();

        newComment.setDate(comment.getDate());
        newComment.setComment(comment.getComment());
        newComment.setUser(user);
        newComment.setFitnessProgram(fitnessProgram);

        return commentRepository.save(newComment);
    }
}
