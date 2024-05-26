package app.service;

import app.dtos.CommentDTO;
import app.entities.Comment;
import app.entities.FitnessProgram;
import app.entities.Log;
import app.entities.User;
import app.repositories.CommentRepository;
import app.repositories.FitnessProgramRepository;
import app.repositories.LogRepository;
import app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final FitnessProgramRepository fitnessProgramRepository;
    private final LogRepository logRepository;
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllCommentsForProgram(int id) { return commentRepository.findAllByFitnessProgramId(id);}

    public Comment createComment(CommentDTO comment, int fitnessProgramId, String username) {
        FitnessProgram fitnessProgram = fitnessProgramRepository.findById(fitnessProgramId)
                .orElseThrow(() -> new IllegalArgumentException("Fitness program not found with id: " + fitnessProgramId));

        User user = userRepository.findByUsername(username);

        Comment newComment = new Comment();

        newComment.setDate(comment.getDate());
        newComment.setComment(comment.getComment());
        newComment.setUser(user);
        newComment.setFitnessProgram(fitnessProgram);

        Log log = Log.builder()
                .event("New Comment added: " + newComment.getComment())
                .date(new Date())
                .build();

        logRepository.save(log);

        return commentRepository.save(newComment);
    }
}
