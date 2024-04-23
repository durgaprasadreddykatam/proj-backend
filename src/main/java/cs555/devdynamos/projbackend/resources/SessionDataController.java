package cs555.devdynamos.projbackend.resources;

import cs555.devdynamos.projbackend.Entities.TestSession;
import cs555.devdynamos.projbackend.service.FetchTestSessionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SessionDataController {

    @Autowired
    FetchTestSessionDataService sessionDataService;

    @GetMapping("/api/fetchUserTestSessions")
    public ResponseEntity<List<Map<String,Object>>> fetchSessionData(@RequestParam String userId){
        List<Map<String,Object>> list =sessionDataService.fetchUserSessionData(UUID.fromString(userId));
        return new ResponseEntity<>(list,HttpStatus.OK) ;
    }
}
