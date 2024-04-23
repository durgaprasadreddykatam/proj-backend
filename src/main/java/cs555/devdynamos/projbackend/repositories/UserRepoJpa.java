package cs555.devdynamos.projbackend.repositories;

import cs555.devdynamos.projbackend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepoJpa extends JpaRepository<User, UUID> {

    User findByEmail(String email) ;
    @Query("select count(u) from userdetails u where u.email = :email")
    Integer getCountByEmail(String email);
}
