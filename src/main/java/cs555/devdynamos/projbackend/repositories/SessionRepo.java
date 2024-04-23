package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.Entities.TestSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepo extends JpaRepository<TestSession, UUID> {

    List<TestSession> findAllByUserIdIsOrderBySessionStartTimeStamp(UUID userID);
}
