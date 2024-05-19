package app.repositories;

import app.entities.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal, Integer> {
    @Query("SELECT j FROM Journal j WHERE j.user.id = :user_id")
    List<Journal> findByUserId(@Param("user_id") int user_id);
}
