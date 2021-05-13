package pl.szczypkowski.blog.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.szczypkowski.blog.Models.FileData;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.FileRepo;
import pl.szczypkowski.blog.Repos.PostRepo;
import pl.szczypkowski.blog.Repos.UserRepo;
import pl.szczypkowski.blog.Service.FileUploadUtil;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class FileController {
    private FileRepo fileRepo;
    private UserRepo userRepo;
    private PostRepo postRepo;

    public FileController(FileRepo fileRepo, UserRepo userRepo, PostRepo postRepo) {
        this.fileRepo = fileRepo;
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @RequestMapping(value = "/addFile" ,method = RequestMethod.GET)
    public String addFile(Model model)
    {
        model.addAttribute("file",new FileData());
        return "addFile";
    }


    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, FileData filedata, Principal principal) throws IOException
    {
        String name =principal.getName();
        Optional<User> userOpitonal =userRepo.findByUsername(name);
        ArrayList<Post> postList = postRepo.findByUser(userOpitonal.get());


        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        if(file.isEmpty())
        {
            return "postUploadSuccess";
        }
        else {
            filedata.setName(fileName);
            filedata.setType(file.getContentType());
            filedata.setPost(postList.get(postList.size()-1));
            FileData savefile = fileRepo.save(filedata);
            String uploadDir = "post-files/" + savefile.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }


        return "postUploadSuccess";
    }
}
