package app.service;

import app.dtos.ExerciseDTO;
import app.entities.Exercise;
import app.repositories.ExerciseRepository;
import app.repositories.ProgramExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final ProgramExerciseRepository programExerciseRepository;
    public List<ExerciseDTO> getExercises(int id) {
        return programExerciseRepository.findAllByFitnessProgramId(id).stream().map(
                programExercise -> new ExerciseDTO(programExercise.getDuration(),programExercise.getExercise().getName(), programExercise.getRepetition())
        ).collect(Collectors.toList());
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }
}
