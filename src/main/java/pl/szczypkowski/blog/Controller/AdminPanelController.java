package pl.szczypkowski.blog.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.PostRepo;
import pl.szczypkowski.blog.Repos.UserRepo;
import pl.szczypkowski.blog.Service.PostService;
import pl.szczypkowski.blog.Service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminPanelController {

    UserRepo userRepo;
    PostRepo postRepo;
    PostService postService;
    UserService userService;

    public AdminPanelController(UserRepo userRepo, PostRepo postRepo, PostService postService, UserService userService) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value="/adminPanel",method = RequestMethod.GET)
    public String adminPanel(Principal principal, Model model)
    {
        List<User> userList=(List<User>) userService.findAll();
        List<Post> postList=(List<Post>) postService.findAll();
        model.addAttribute("users",userList);
        model.addAttribute("posts",postList);
        return"adminPanel";
    }

    @GetMapping("/editUser/{id}")
    public String edit(@PathVariable("id")long id ,Model model)
    {
        User user =userRepo.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Invalid user ID:" +id)
        );
        model.addAttribute("user",user);
        return "edit";
    }
    @GetMapping("/editPost/{id}")
    public String editPost(@PathVariable("id")long id , Model model)
    {
        Post post = postRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid post ID:"+id));
        model.addAttribute("post",post);
        return "editPost";
    }


    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult bindingResult,Model model)
    {
        if(bindingResult.hasErrors())
        {
            user.setUser_id(id);
            return "redirect:/edit";
        }
        userService.addUser(user);
        return "redirect:/adminPanel";
    }
    @PostMapping("/updatePost/{id}")
    public String updatePost(@PathVariable("id") long id , Post post,BindingResult bindingResult,Model model)
    {
        post.setPost_id(id);
        postService.addPost(post);
        return "redirect:/adminPanel";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id")long id,Model model){
        User user=userRepo.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Invalid user ID:" +id)
        );
        userRepo.delete(user);
        return"redirect:/adminPanel";
    }
    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable("id")long id,Model model)
    {
        Post post=postRepo.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Invalid post ID:" +id)
        );
        postRepo.delete(post);
        return "redirect:/adminPanel";
    }






}
