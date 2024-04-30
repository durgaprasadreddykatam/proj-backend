package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.Entities.TestSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepo extends JpaRepository<TestSession, UUID> {

    List<TestSession> findAllByUserIdIsOrderBySessionStartTimeStamp(UUID userID);

    @Query("SELECT s FROM TestSession s WHERE s.userId = :userId AND s.testOrTrain = 'Test' ORDER BY s.sessionStartTimeStamp DESC")
    List<TestSession> getByUserId(UUID userId);
}
