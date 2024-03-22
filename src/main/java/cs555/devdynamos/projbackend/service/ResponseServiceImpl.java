package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.domain.UserResponse;
import cs555.devdynamos.projbackend.repositories.IUserResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResponseServiceImpl implements IResponseService {
    @Autowired
    IUserResponseRepository responseRepository;
    @Override
    public UUID addUserResponse(UserResponse userResponse) {
        return responseRepository.addUserResponse(userResponse);
    }
}
