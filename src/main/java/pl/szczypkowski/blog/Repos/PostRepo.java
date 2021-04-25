package pl.szczypkowski.blog.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczypkowski.blog.Models.Post;

public interface PostRepo extends JpaRepository<Post,Long> {

}
