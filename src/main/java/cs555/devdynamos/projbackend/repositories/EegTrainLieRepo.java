package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.Entities.EegDataTestLie;
import cs555.devdynamos.projbackend.Entities.EegDataTrainLie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EegTrainLieRepo extends JpaRepository<EegDataTrainLie,Long> {
}
