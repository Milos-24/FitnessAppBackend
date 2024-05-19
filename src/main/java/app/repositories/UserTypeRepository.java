package app.repositories;

import app.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    @Query("SELECT u FROM UserType u WHERE u.type = :type")
    UserType findByType(@Param("type") String type);
}
