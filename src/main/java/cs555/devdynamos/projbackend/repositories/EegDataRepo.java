package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.Entities.EegString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EegDataRepo extends JpaRepository<EegString, UUID> {
}
