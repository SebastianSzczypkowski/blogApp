package pl.szczypkowski.blog.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczypkowski.blog.Models.Comment;
import pl.szczypkowski.blog.Models.FileData;
import pl.szczypkowski.blog.Models.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {

    List<Comment> findByPost(Post post);
}
