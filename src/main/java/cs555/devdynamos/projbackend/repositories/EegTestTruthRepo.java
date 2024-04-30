package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.Entities.EegDataTestLie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EegTestLieRepo extends JpaRepository<EegDataTestLie,Long> {
}
