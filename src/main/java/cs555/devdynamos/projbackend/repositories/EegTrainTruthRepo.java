package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.Entities.EegDataTestLie;
import cs555.devdynamos.projbackend.Entities.EegDataTrainTruth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EegTrainTruthRepo extends JpaRepository<EegDataTrainTruth,Long> {
}
