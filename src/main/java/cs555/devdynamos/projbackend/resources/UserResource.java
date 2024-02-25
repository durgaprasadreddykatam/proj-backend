package cs555.devdynamos.projbackend.resources;

import cs555.devdynamos.projbackend.domain.User;
import cs555.devdynamos.projbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> userLogin(@RequestBody Map<String,Object> userMap){
        String email=(String) userMap.get("email");
        String password=(String) userMap.get("password");
        User user=userService.validateUser(email,password);
        Map<String,String> map=new HashMap<>();
        map.put("message","logged in  Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String,Object> userMap){
        String firstname=(String) userMap.get("firstName");
        String lastname=(String) userMap.get("lastName");
        String email=(String) userMap.get("email");
        String password=(String) userMap.get("password");
        User user =userService.registerUser(firstname,lastname,email,password);
        Map<String,String> map=new HashMap<>();
        map.put("message","registered Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
