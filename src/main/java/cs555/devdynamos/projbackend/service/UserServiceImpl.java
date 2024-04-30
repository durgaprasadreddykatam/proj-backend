package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.User;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import cs555.devdynamos.projbackend.repositories.UserRepoJpa;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.Random;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepoJpa repository;
    @Override
    public User validateUser(User user) throws EtAuthException {
            if (user.getEmail() != null)
            user.setEmail(user.getEmail().toLowerCase());
            User userDet=repository.findByEmail(user.getEmail());
            if(!BCrypt.checkpw(user.getPassword(),userDet.getPassword()))
                throw new EtAuthException("Invalid email/password");

        return userDet;
    }

    @Override
    public User registerUser(User user) throws EtAuthException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(7));
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (user.getEmail() != null)
            user.setEmail(user.getEmail().toLowerCase());
        if (!pattern.matcher(user.getEmail()).matches()) throw new EtAuthException("Invalid email format");
        Integer count = repository.getCountByEmail(user.getEmail());
        if (count > 0) throw new EtAuthException("Email already in use");
        user.setPassword(hashedPassword);
        Random random = new Random();
        user.setAssignedNumber(random.nextInt(27) + 1);
        user.setIntroTestTakenAsTruthTeller(false);
        user.setIntroTestTakenAsLiar(false);
        user.setIntroSeen(false);
        return  repository.save(user);
    }

    @Override
    public User updateUser(User user) throws EtAuthException {
        if (user.getEmail() != null)
            user.setEmail(user.getEmail().toLowerCase());
        if(user.getPassword()!=null) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(7));
            user.setPassword(hashedPassword);
        }
        repository.save(user);
            return user;
    }



    @Override
    public User updateIntroSeen(String userId, boolean introSeen) {
        UUID id=UUID.fromString(userId);
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setIntroSeen(introSeen);
            repository.save(user);
            return user;
        }
        else{
            throw new EtAuthException("Invalid User Id");
        }
    }

    @Override
    public User getUser(UUID userId) {
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        }
        else{
            throw new EtAuthException("Invalid User Id");
        }

    }

    @Override
    public User updateUserDetails(User user) {
        return repository.save(user);
    }


}
