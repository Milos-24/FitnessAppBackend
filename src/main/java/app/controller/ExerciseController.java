package app.controller;

import app.dtos.ExerciseDTO;
import app.entities.Exercise;
import app.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises")
@CrossOrigin(origins = "http://localhost:4200")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/{id}")
    public List<ExerciseDTO> getExercises(@PathVariable("id") int id) {
        return exerciseService.getExercises(id);
    }

    @GetMapping
    public List<Exercise> getAllExercises()
    {
        return exerciseService.getAllExercises();
    }
}
