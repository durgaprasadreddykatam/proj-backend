package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.UserResponse;
import cs555.devdynamos.projbackend.repositories.UserResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
public class ResponseService implements IResponseService{
    @Autowired
    UserResponseRepo userResponseRepo;
    @Override
    public UUID addUserResponse(UserResponse userResponse) {
        userResponseRepo.save(userResponse);
        return userResponse.getUserResponseID();
    }
}
