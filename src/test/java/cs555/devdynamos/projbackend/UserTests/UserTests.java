package cs555.devdynamos.projbackend.UserTests;

import cs555.devdynamos.projbackend.domain.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.repositories.UserRepository;
import cs555.devdynamos.projbackend.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.regex.Pattern;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
//    @Test
//    public void registerUserTest(){
//         User user=new User();
//         user.setFirstName("Kumar");
//         user.setLastName("Test");
//         user.setEmail("testemail@test.com");
//         user.setPassword("password");
//        User user = userRegistrationService.registerUser("John", "Doe", "test@example.com", "password");
//    }



//    @Override
//    public User validateUser(String email, String password) throws EtAuthException {
//        if (email != null) email = email.toLowerCase();
//        return userRepository.findByEmailAndPassword(email, password);
//    }
//
//    @Override
//    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
//        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
//        if (email != null) email = email.toLowerCase();
//        if (!pattern.matcher(email).matches()) throw new EtAuthException("Invalid email format");
//        Integer count = userRepository.getCountByEmail(email);
//        if (count > 0) throw new EtAuthException("Email already in use");
//        UUID userId = userRepository.create(firstName, lastName, email, password);
//        return userRepository.findById(userId);
//    }
//
//    @Override
//    public User updateUser(String firstName, String lastName, String email, String password) throws EtAuthException {
//        if (email != null) email = email.toLowerCase();
//        Integer count = userRepository.getCountByEmail(email);
//        if (count < 0) throw new EtAuthException("Email id not in Use");
//        return userRepository.update(firstName, lastName, email, password);
//    }
//
//    @Override
//    public User updateUser(String firstName, String lastName, String email) throws EtAuthException {
//        if (email != null) email = email.toLowerCase();
//        Integer count = userRepository.getCountByEmail(email);
//        if (count < 0) throw new EtAuthException("Email id not in Use");
//        return userRepository.update(firstName, lastName, email);
//
//    }

//    private UserResource userResource;
}
