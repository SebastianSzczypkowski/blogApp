package pl.szczypkowski.blog.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Models.User;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {


    Optional<Post> findByTopic(String topic);
    //Optional<Post> findByUser(User user);
    ArrayList<Post> findByUser(User user);
}
