package pl.szczypkowski.blog.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczypkowski.blog.Models.Token;

@Repository
public interface TokenRepo extends JpaRepository<Token,Long> {

    Token findByToken(String token);
}
