package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;

import java.util.UUID;

public interface UserService {

    User validateUser(User user) throws EtAuthException;

    User registerUser(User user) throws EtAuthException;

    User updateUser(User user) throws EtAuthException;

    User updateIntroSeen(String userId, boolean introSeen);

    User getUser(UUID userId);

    User updateUserDetails(User user);
}
