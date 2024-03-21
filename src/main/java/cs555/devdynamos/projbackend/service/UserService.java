package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.domain.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;

import java.util.UUID;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser( String firstName, String lastName, String email, String password) throws EtAuthException;

    User updateUser( String firstName, String lastName, String email, String password) throws EtAuthException;

    User updateUser( String firstName, String lastName, String email) throws EtAuthException;

    String updateIntroTest(String userId, boolean introTestTaken);

    String updateIntroSeen(String userId, boolean introSeen);
}
