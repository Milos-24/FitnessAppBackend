package app.repositories;

import app.entities.Category;
import app.entities.FitnessProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitnessProgramRepository extends JpaRepository<FitnessProgram, Integer>, JpaSpecificationExecutor<FitnessProgram> {
    @Query("SELECT f FROM FitnessProgram f WHERE f.category = :category")
    List<FitnessProgram> findByCategory(@Param("category") Category category);

    @Query("SELECT f FROM FitnessProgram f WHERE f.location = :location")
    List<FitnessProgram> findByLocation(@Param("location") String location);


}
