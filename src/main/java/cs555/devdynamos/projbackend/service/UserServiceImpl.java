package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.domain.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if (email != null) email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) email = email.toLowerCase();
        if (!pattern.matcher(email).matches()) throw new EtAuthException("Invalid email format");
        Integer count = userRepository.getCountByEmail(email);
        if (count > 0) throw new EtAuthException("Email already in use");
        UUID userId = userRepository.create(firstName, lastName, email, password);
        return userRepository.findById(userId);
    }

    @Override
    public User updateUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        if (email != null) email = email.toLowerCase();
        Integer count = userRepository.getCountByEmail(email);
        if (count < 0) throw new EtAuthException("Email id not in Use");
        return userRepository.update(firstName, lastName, email, password);
    }

    @Override
    public User updateUser(String firstName, String lastName, String email) throws EtAuthException {
        if (email != null) email = email.toLowerCase();
        Integer count = userRepository.getCountByEmail(email);
        if (count < 0) throw new EtAuthException("Email id not in Use");
        return userRepository.update(firstName, lastName, email);

    }
}
