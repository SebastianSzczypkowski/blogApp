package pl.szczypkowski.blog.Controller;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.szczypkowski.blog.Models.Comment;
import pl.szczypkowski.blog.Models.FileData;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.CommentRepo;
import pl.szczypkowski.blog.Repos.FileRepo;
import pl.szczypkowski.blog.Repos.PostRepo;
import pl.szczypkowski.blog.Repos.UserRepo;
import pl.szczypkowski.blog.Service.PostService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {


    private PostService postService;
    private CommentRepo commentRepo;
    private UserRepo userRepo;
    private PostRepo postRepo;
    private FileRepo fileRepo;


    public HomeController(PostService postService, CommentRepo commentRepo, UserRepo userRepo, PostRepo postRepo, FileRepo fileRepo) {
        this.postService = postService;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.fileRepo = fileRepo;
    }

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(Principal principal, Model model)
    {
        Collection<?extends GrantedAuthority> authorities= SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("authorities",authorities);
        List<Post> postList =(List<Post>) postService.findAll();

        List<FileData> files = fileRepo.findAll();
        model.addAttribute("files",files);
        model.addAttribute("posts",postList);
        model.addAttribute("name",principal.getName());
        model.addAttribute("newComment",new Comment());

        return "home";
    }

    @PostMapping("/addYourComment")
    public String addComment(@ModelAttribute("newComment")Comment comment,Principal principal ,Model model)
    {
        String name =principal.getName();
        Optional<User> userOpitonal =userRepo.findByUsername(name);
        ArrayList<Post> postOptional = postRepo.findByUser(userOpitonal.get());
        if(comment.getText()==null)
        {
            return "redirect:/home";
        }
        else
        {
            comment.setPost(postOptional.get(0));
            Comment savecomment=commentRepo.save(comment);
            return "redirect:/home";
        }
    }

}
