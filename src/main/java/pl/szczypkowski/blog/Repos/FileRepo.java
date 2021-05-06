package pl.szczypkowski.blog.Repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczypkowski.blog.Models.FileData;

import java.util.Optional;

@Repository
public interface FileRepo extends JpaRepository<FileData,Long> {

   Optional<FileData> findByName(String filename);
}
