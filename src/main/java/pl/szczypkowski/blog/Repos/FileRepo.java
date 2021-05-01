package pl.szczypkowski.blog.Repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczypkowski.blog.Models.FileDB;

import java.util.Optional;

@Repository
public interface FileRepo extends JpaRepository<FileDB,Long> {

   Optional<FileDB> findByName(String filename);
}
