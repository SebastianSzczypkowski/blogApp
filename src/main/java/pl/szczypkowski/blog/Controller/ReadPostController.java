package pl.szczypkowski.blog.Controller;


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
import java.util.List;
import java.util.Optional;

@Controller
public class ReadPostController {

    private PostService postService;
    private CommentRepo commentRepo;
    private UserRepo userRepo;
    private PostRepo postRepo;
    private FileRepo fileRepo;

    public ReadPostController(PostService postService, CommentRepo commentRepo, UserRepo userRepo, PostRepo postRepo, FileRepo fileRepo) {
        this.postService = postService;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.fileRepo = fileRepo;
    }

    @GetMapping("/readPost/{id}")
    public String readPost(@PathVariable("id") long id, Model model)
    {
        Post post =postRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid post ID:" +id));
        Optional<FileData> files = fileRepo.findByPost(post);
        if(!files.isPresent()){

        }
        else {
            model.addAttribute("files",files.get());
        }

       List<Comment> comments= commentRepo.findByPost(post);

        model.addAttribute("comments",comments);
        model.addAttribute("post",post);
        model.addAttribute("newComment", new Comment());

        return "post";

    }
    @PostMapping("/addYourComment/{id}")
    public String addComment(@PathVariable("id") long id,@ModelAttribute("newComment") Comment comment, Principal principal , Model model)
    {
        String name =principal.getName();
        Optional<User> userOpitonal =userRepo.findByUsername(name);
        Optional<Post> postOptional =postRepo.findById(id);


            if(!userOpitonal.isPresent()){

                return "redirect:/readPost/{id}";
            }
            if(comment.getText()==null)
            {
                return "redirect:/readPost/{id}";
            }
            else
            {
            comment.setUser(userOpitonal.get());
            comment.setPost(postOptional.get());
            Comment savecomment=commentRepo.save(comment);
            return "redirect:/readPost/{id}";
        }
    }


}
