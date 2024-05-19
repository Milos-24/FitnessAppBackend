package app.service;


import app.entities.Exercise;
import app.entities.FitnessProgram;
import app.entities.ProgramExercise;
import app.repositories.ExerciseRepository;
import app.repositories.FitnessProgramRepository;
import app.repositories.ProgramExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramExerciseService {
    private final ProgramExerciseRepository programExerciseRepository;
    private final FitnessProgramRepository fitnessProgramRepository;
    private final ExerciseRepository exerciseRepository;
    public ResponseEntity<?> addProgramExercise(int fitnessProgramId, int exerciseId, String repetition, String duration) {
        FitnessProgram fitnessProgram = fitnessProgramRepository.findById(fitnessProgramId).orElse(null);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);

        ProgramExercise programExercise = ProgramExercise.builder()
                .fitnessProgram(fitnessProgram)
                .exercise(exercise)
                .repetition(repetition)
                .duration(duration)
                .build();

        programExerciseRepository.save(programExercise);

        if (fitnessProgram != null) {
            fitnessProgram.getProgramExercises().add(programExercise);
            fitnessProgramRepository.save(fitnessProgram);
        }

        return ResponseEntity.ok(programExercise);
    }
}
