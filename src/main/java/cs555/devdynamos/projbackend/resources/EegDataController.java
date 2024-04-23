package cs555.devdynamos.projbackend.resources;

import cs555.devdynamos.projbackend.Entities.EegData;
import cs555.devdynamos.projbackend.service.EegDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/eegdata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EegDataController {
//    @Autowired
//    private EegDataService eegDataService;
//
//    @PutMapping
//    public ResponseEntity saveEegdata(@RequestBody Map<String,Object> map){
//        UUID sessionId= UUID.fromString((String)map.get("sessionId"));
//        double AF3 =(Double)map.get("EEG.AF3");
//        double T7=(Double)map.get("EEG.T7");
//        double Pz=(Double)map.get("EEG.Pz");
//        double T8=(Double)map.get("EEG.T8");
//        double AF4=(Double)map.get("EEG.AF4");
//        EegData data=new EegData(sessionId,AF3,T7,Pz,T8,AF4);
//        boolean value=eegDataService.updateeegData(data);
//        return new ResponseEntity<>(Map.of("message",value), HttpStatus.OK);//change "value" to value
//
//    }
}
