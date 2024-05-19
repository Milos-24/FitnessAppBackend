package app.repositories;

import app.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    @Query("SELECT m FROM Membership m WHERE m.user.id = :user_id")
    List<Membership> findByUserId(@Param("user_id") int user_id);
}
