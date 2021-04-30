package pl.szczypkowski.blog.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.PostRepo;
import pl.szczypkowski.blog.Repos.UserRepo;
import pl.szczypkowski.blog.Service.PostService;
import pl.szczypkowski.blog.Service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.Optional;

@Controller
public class PostController {

    PostRepo postRepo;
    UserService userService;
    UserRepo userRepo;
    PostService postService;

    @Autowired
    public PostController(PostRepo postRepo, UserService userService, UserRepo userRepo, PostService postService) {
        this.postRepo = postRepo;
        this.userService = userService;
        this.userRepo = userRepo;
        this.postService = postService;
    }

    @GetMapping("/addPost")
    public String get(Model model )
    {

        model.addAttribute("newPost",new Post());
        return "addpost";
    }

    @PostMapping("/addYourPost" )
    public String addPost( @ModelAttribute("newPost")@Valid Post post, BindingResult bindingResult,Principal principal)
    {
        String name =principal.getName();
        Optional<User>userOpitonal =userRepo.findByUsername(name);
        if(!userOpitonal.isPresent()){

            return "addpost";
        }

        if(bindingResult.hasErrors())
        {

            return "addpost";
        }
        else{
            post.setUser(userOpitonal.get());
            Post savepost=postRepo.save(post);
            return "postUploadSuccess";
        }
    }

}
