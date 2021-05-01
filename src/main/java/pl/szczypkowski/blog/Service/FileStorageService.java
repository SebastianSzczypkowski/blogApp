package pl.szczypkowski.blog.Service;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.szczypkowski.blog.Models.FileDB;
import pl.szczypkowski.blog.Repos.FileRepo;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private FileRepo fileRepo;
    public FileDB store(MultipartFile multipartFile)throws IOException
    {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName,multipartFile.getBytes());
        return fileRepo.save(fileDB);
    }

    public FileDB getFile(String id)
    {
        return fileRepo.findById(id).get();
    }
    public Stream<FileDB> getAllFilles()
    {
        return fileRepo.findAll().stream();
    }



}
