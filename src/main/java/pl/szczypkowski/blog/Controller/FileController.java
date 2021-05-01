package pl.szczypkowski.blog.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.szczypkowski.blog.Models.FileDB;
import pl.szczypkowski.blog.Models.ResponseMessage;
import pl.szczypkowski.blog.Service.FileStorageService;

import java.io.IOException;

@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger("FileController.class");
    private FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @RequestMapping(value="/addFile",method = RequestMethod.GET)
    public String get(Model model)
    {
       // model.addAttribute("newFile",new FileDB());
        return "addFile";
    }
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("name") String name, @RequestParam("file")MultipartFile file)
    {
        return "addpost";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = fileStorageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}


