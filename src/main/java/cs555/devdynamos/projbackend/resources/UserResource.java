package cs555.devdynamos.projbackend.resources;

import cs555.devdynamos.projbackend.Constants;
import cs555.devdynamos.projbackend.Entities.User;
import cs555.devdynamos.projbackend.service.MockEegDataService;
import cs555.devdynamos.projbackend.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserResource {
    @Autowired
    UserService userService;



    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> userLogin(@RequestBody Map<String,Object> userMap){
        User user =new User();
        user.setEmail((String) userMap.get("email"));
        user.setPassword((String) userMap.get("password"));
        User userDet=userService.validateUser(user);
        return new ResponseEntity<>(generateJwtToken(userDet), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String,Object> userMap){
        User user =new User();
        user.setEmail((String) userMap.get("email"));
        user.setFirstName((String) userMap.get("firstName"));
        user.setLastName((String) userMap.get("lastName"));
        user.setPassword((String) userMap.get("password"));
        User userDetails =userService.registerUser(user);
        return new ResponseEntity<>(generateJwtToken(userDetails), HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<Map<String,String>> updateUser(@RequestBody Map<String,Object> userMap){
        User user=new User();
        if (userMap.containsKey("password")) {
            user.setPassword((String) userMap.get("password"));
        }
        if(userMap.containsKey("email")){
            user.setFirstName((String) userMap.get("firstName"));
            user.setLastName((String) userMap.get("lastName"));
            user.setEmail((String) userMap.get("email"));

        }
        User userDet=userService.updateUser(user);
        return new ResponseEntity<>(generateJwtToken(userDet), HttpStatus.OK);
    }
    @PostMapping("/introTestUpdate")
    public ResponseEntity<Map<String,String>> updateIntroTaken(@RequestBody Map<String,Object> userMap){
        User user=userService.getUser(UUID.fromString((String) userMap.get("userId")));
        if(userMap.containsKey("introTestTakenAsLiar")){
            boolean introTestTakenAsLiar = (boolean) userMap.get("introTestTakenAsLiar");
            user.setIntroTestTakenAsLiar(introTestTakenAsLiar);
        }
        else if(userMap.containsKey("introTestTakenAsTruthTeller")){
            boolean introTestTakenAsTruthTeller =(boolean) userMap.get("introTestTakenAsTruthTeller");
            user.setIntroTestTakenAsTruthTeller(introTestTakenAsTruthTeller);

        }
        User returnuser=userService.updateUserDetails(user);
        return new ResponseEntity<>(generateJwtToken(returnuser), HttpStatus.OK);

    }
    @PostMapping("/introSeenUpdate")
    public ResponseEntity<Map<String,String>> updateIntroSeen(@RequestBody Map<String,Object> userMap){
        String userId=(String) userMap.get("userId");
        boolean introSeen =(boolean) userMap.get("introSeen");
        User user=userService.updateIntroSeen(userId,introSeen);
        return new ResponseEntity<>(generateJwtToken(user), HttpStatus.OK);

    }
    private Map<String,String> generateJwtToken(User user){
        long timestamp=System.currentTimeMillis();
        String token= Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp+Constants.TOKENVALIDITY))
                .claim("userId",user.getUserId())
                .claim("email",user.getEmail())
                .claim("firstname",user.getFirstName())
                .claim("lastname",user.getLastName())
                .claim("introSeen",user.isIntroSeen())
                .claim("introTestTakenAsLiar",user.isIntroTestTakenAsLiar())
                .claim("introTestTakenAsTruthTeller",user.isIntroTestTakenAsTruthTeller())
                .claim("assignedNumber",user.getAssignedNumber())
                .compact();
        Map <String,String> map=new HashMap<>();
        map.put("token",token);
        return map;


    }
}
