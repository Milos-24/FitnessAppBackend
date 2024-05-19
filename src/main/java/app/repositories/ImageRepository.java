package app.repositories;

import app.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query("SELECT i FROM Image i WHERE i.fitnessProgram.id = :fitness_program_id")
    List<Image> findAllByFitnessProgramId(@Param("fitness_program_id") int fitness_program_id);
}
