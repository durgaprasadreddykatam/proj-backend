package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;

public interface UserService {

    User validateUser(User user) throws EtAuthException;

    User registerUser(User user) throws EtAuthException;

    User updateUser(User user) throws EtAuthException;

    String updateIntroTest(String userId, boolean introTestTaken);

    String updateIntroSeen(String userId, boolean introSeen);
}
