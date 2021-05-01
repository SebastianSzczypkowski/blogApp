package pl.szczypkowski.blog.Repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczypkowski.blog.Models.FileDB;

@Repository
public interface FileRepo extends JpaRepository<FileDB,String> {


}
