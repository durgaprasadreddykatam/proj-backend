package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.domain.User;

import java.util.UUID;

public interface UserRepository {

    UUID create(User user) throws EtAuthException;
    User update(User user) throws EtAuthException;

    User findByEmail(User user) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(UUID userId);


    String updateIntroTest(UUID userId, boolean introTestTaken);

    String updateIntroSeen(UUID userId, boolean introSeen);
}
