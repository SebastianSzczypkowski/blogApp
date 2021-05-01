package pl.szczypkowski.blog.Controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.szczypkowski.blog.Models.FileDB;
import pl.szczypkowski.blog.Repos.FileRepo;
import pl.szczypkowski.blog.Service.FileStorageService;

import java.io.IOException;
import java.util.List;

@Controller
public class FileController {

    private FileRepo fileRepo;
    private FileStorageService fileStorageService;

    public FileController(FileRepo fileRepo, FileStorageService fileStorageService) {
        this.fileRepo = fileRepo;
        this.fileStorageService = fileStorageService;
    }

    @RequestMapping(value="/addFile",method = RequestMethod.GET)
    public String get(Model model)
    {
       // model.addAttribute("newFile",new FileDB());

        return "addFile";
    }
    @PostMapping("/uploadFile")
    public String uploadFile( @RequestParam("file")MultipartFile file) throws  IOException
    {
        FileDB image = new FileDB();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setData(file.getBytes());
        fileRepo.save(image);

        return "redirect:/addpost";
    }


}


