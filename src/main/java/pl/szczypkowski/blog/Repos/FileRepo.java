package pl.szczypkowski.blog.Repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szczypkowski.blog.Models.FileData;
import pl.szczypkowski.blog.Models.Post;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface FileRepo extends JpaRepository<FileData,Long> {


   Optional<FileData> findByName(String filename);
   Optional<FileData> findByPost(Post post);
}
