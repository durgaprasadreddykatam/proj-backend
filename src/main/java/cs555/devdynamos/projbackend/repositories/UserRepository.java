package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.domain.User;

import java.util.UUID;

public interface UserRepository {

    UUID create(String firstName,String lastName,String email,String password) throws EtAuthException;
    User findByEmailAndPassword(String email,String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(UUID userId);


}
