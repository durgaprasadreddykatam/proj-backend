package cs555.devdynamos.projbackend.resources;
import cs555.devdynamos.projbackend.Entities.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.repositories.UserRepoJpa;
import cs555.devdynamos.projbackend.service.MockEegDataService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mockData")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MockDataResource {
    @Autowired
    MockEegDataService mockEegDataService;

    @Autowired
    UserRepoJpa userRepoJpa;

    @GetMapping("/getData")
    public ResponseEntity<Map<String, List<Object>>> getData(@RequestParam String userId) {
        User user= userRepoJpa.findById(UUID.fromString(userId)).orElse(null);
        if(user !=null){
        Map<String, List<Object>> responseData = mockEegDataService.getData(user.getAssignedNumber());
            return new  ResponseEntity(responseData, HttpStatus.OK);
        }
        else throw  new EtAuthException("Invalid User ID");
    }
}
