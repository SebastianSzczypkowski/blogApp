package pl.szczypkowski.blog.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczypkowski.blog.Models.Comment;

public interface CommentRepo extends JpaRepository<Comment,Long> {


}
