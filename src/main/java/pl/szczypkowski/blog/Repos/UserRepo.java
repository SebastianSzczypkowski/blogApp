package pl.szczypkowski.blog.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczypkowski.blog.Models.User;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);


}
