package cs555.devdynamos.projbackend.UserTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cs555.devdynamos.projbackend.domain.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.resources.UserResource;
import cs555.devdynamos.projbackend.service.UserService;


@ExtendWith(MockitoExtension.class)
public class UserResourceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    @Test
    public void testUserLogin_Success() {
        String email = "test@example.com";
        String password = "password";
        User user = new User(UUID.randomUUID(), "John", "Doe", email, password,false,false);
        when(userService.validateUser(user)).thenReturn(user);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", email);
        userMap.put("password", password);
        ResponseEntity<Map<String, String>> responseEntity = userResource.userLogin(userMap);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().containsKey("token"));
    }

    @Test
    public void testUserLogin_InvalidCredentials() {
        User user =new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userService.validateUser(user)).thenThrow(new EtAuthException("Invalid email/password"));

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", user.getEmail());
        userMap.put("password", user.getUserId());
        EtAuthException exception = assertThrows(EtAuthException.class, () -> {
            userResource.userLogin(userMap);
        });

        assertEquals("Invalid email/password", exception.getMessage());
    }


}
