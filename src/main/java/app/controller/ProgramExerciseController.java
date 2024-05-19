package app.controller;

import app.service.ProgramExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/programexercise")
@CrossOrigin(origins = "http://localhost:4200")
public class ProgramExerciseController {

    private final ProgramExerciseService programExerciseService;
    @PostMapping("/{id}")
    public ResponseEntity<?> addProgramExercise(@PathVariable("id") int id,
                                                @RequestParam String duration,
                                                @RequestParam String repetition,
                                                @RequestParam int fitnessProgramId)
    {
        return programExerciseService.addProgramExercise(fitnessProgramId,id,repetition,duration);
    }
}
