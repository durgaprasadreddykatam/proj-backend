package cs555.devdynamos.projbackend.resources;

import cs555.devdynamos.projbackend.Entities.EegString;
import cs555.devdynamos.projbackend.JsonConverter;
import cs555.devdynamos.projbackend.service.EegDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/eegdata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EegDataController {
    @Autowired
    private EegDataService eegDataService;

    @PutMapping
    public ResponseEntity saveEegData(@RequestBody Map<String,Object> map){
        UUID sessionId= UUID.fromString((String)map.get("sessionId"));
        String role =(String)map.get("role");
        String trainOrTest =(String)map.get("trainorTest");
        List<Map<String, Object>> eegDataListMap = (List<Map<String, Object>>) map.get("eegdata");
        String jsonData = JsonConverter.convertToJSON(eegDataListMap);
        EegString eegString=new EegString();
        eegString.setEegJsonString(jsonData);
        eegString.setSessionId(sessionId);
        eegString.setRole(role);
        eegString.setTrainData(trainOrTest);
        eegString.setTimestamp(new Timestamp(System.currentTimeMillis()));
        boolean value = eegDataService.updateEegData(eegString);
        return new ResponseEntity<>(Map.of("message",value), HttpStatus.OK);

    }

}
