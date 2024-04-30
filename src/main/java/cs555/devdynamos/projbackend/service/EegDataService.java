package cs555.devdynamos.projbackend.service;
import cs555.devdynamos.projbackend.Entities.EegString;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.repositories.EegDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EegDataService {

    @Autowired
    EegDataRepo eegDataRepo;


    public boolean updateEegData(EegString data){

        try{
            eegDataRepo.save(data);
            return true;

        }catch(Exception e){
            throw new EtAuthException("Failed to write data to database");
        }
    }

}
