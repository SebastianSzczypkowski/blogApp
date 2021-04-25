package pl.szczypkowski.blog.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Repos.PostRepo;
import pl.szczypkowski.blog.Repos.UserRepo;
import pl.szczypkowski.blog.Service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PostController {

    PostRepo postRepo;
    UserService userService;
    UserRepo userRepo;

    public PostController(PostRepo postRepo, UserService userService, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping("/addPost")
    public String get(Model model )
    {

        model.addAttribute("newPost",new Post());
        return "addpost";
    }

    @PostMapping("/addYourPost")
    public String addPost(@ModelAttribute("newPost")@Valid Post post, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "addpost";
        }
        else{

            postRepo.save(post);
            return "postUploadSuccess";
        }
    }

}
