
package cs555.devdynamos.projbackend.UserTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

import cs555.devdynamos.projbackend.domain.User;
import cs555.devdynamos.projbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDetailsUpdateTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private UserService userService;

    @Test
    public void testUpdateUser() throws Exception {

        User updatedUser = new User();
        updatedUser.setEmail("test@example.com");


        when(userService.updateUser(any(User.class))).thenReturn(updatedUser);


        Map<String, Object> userMapWithEmailAndPassword = new HashMap<>();
        userMapWithEmailAndPassword.put("email", "test@example.com");
        userMapWithEmailAndPassword.put("password", "newPassword");


        MockHttpServletRequestBuilder requestBuilder1 = post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userMapWithEmailAndPassword));
        mockMvc.perform(requestBuilder1).andExpect(status().isOk());


        Map<String, Object> userMapWithEmailOnly = new HashMap<>();
        userMapWithEmailOnly.put("email", "test@example.com");


        MockHttpServletRequestBuilder requestBuilder2 = post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userMapWithEmailOnly));
        mockMvc.perform(requestBuilder2).andExpect(status().isOk());
    }


    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
