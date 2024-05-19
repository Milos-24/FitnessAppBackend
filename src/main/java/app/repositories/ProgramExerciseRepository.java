package app.repositories;

import app.entities.ProgramExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramExerciseRepository extends JpaRepository<ProgramExercise, Integer> {
    List<ProgramExercise> findAllByFitnessProgramId(final int fitnessProgramId);

}
