package cs555.devdynamos.projbackend.resources;

import cs555.devdynamos.projbackend.Constants;
import cs555.devdynamos.projbackend.domain.User;
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

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserResource {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> userLogin(@RequestBody Map<String,Object> userMap){
        String email=(String) userMap.get("email");
        String password=(String) userMap.get("password");
        User user=userService.validateUser(email,password);
        return new ResponseEntity<>(generateJwtToken(user), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String,Object> userMap){
        String firstname=(String) userMap.get("firstName");
        String lastname=(String) userMap.get("lastName");
        String email=(String) userMap.get("email");
        String password=(String) userMap.get("password");
        User user =userService.registerUser(firstname,lastname,email,password);
        return new ResponseEntity<>(generateJwtToken(user), HttpStatus.OK);
    }
    @PostMapping("/update")
    public String updateUser(@RequestBody Map<String,Object> userMap){
        String firstname=(String) userMap.get("firstName");
        String lastname=(String) userMap.get("lastName");
        String email=(String) userMap.get("email");
        String password=(String) userMap.get("password");
        User user=userService.updateUser(firstname,lastname,email,password);
        return "User Details have been Sucessfully Updated for user :"+user.getEmail();
    }
    @PostMapping("/update1")
    public String updateUser1(@RequestBody Map<String,Object> userMap){
        String firstname=(String) userMap.get("firstName");
        String lastname=(String) userMap.get("lastName");
        String email=(String) userMap.get("email");
        User user=userService.updateUser(firstname,lastname,email);
        return "User Details have been Sucessfully Updated for user :"+user.getEmail();
    }
    @PostMapping("/introTestUpdate")
    public String updateIntroTaken(@RequestBody Map<String,Object> userMap){
        String userId=(String) userMap.get("userId");
        boolean introTestTaken =(boolean) userMap.get("introTestTaken");
        String user=userService.updateIntroTest(userId,introTestTaken);
        return "Intro Test has Been Marked as Completed for User:"+user;

    }
    @PostMapping("/introSeenUpdate")
    public String updateIntroSeen(@RequestBody Map<String,Object> userMap){
        String userId=(String) userMap.get("userId");
        boolean introSeen =(boolean) userMap.get("introSeen");
        String user=userService.updateIntroSeen(userId,introSeen);
        return "Intro Seen has Been Marked as Completed for User:"+user;

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
                .claim("introTestTaken",user.isIntroTestTaken())
                .compact();
        Map <String,String> map=new HashMap<>();
        map.put("token",token);
        return map;


    }
}
