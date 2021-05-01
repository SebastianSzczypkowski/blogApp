package pl.szczypkowski.blog.Controller;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.PostRepo;
import pl.szczypkowski.blog.Repos.UserRepo;
import pl.szczypkowski.blog.Service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {

    UserRepo userRepo;
    UserService userService;
    PostRepo postRepo;
    public UserController(UserRepo userRepo, UserService userService, PostRepo postRepo) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.postRepo = postRepo;
    }
    @GetMapping("/signup")
    public String get(Model model)
    {

        model.addAttribute("newUser",new User());
        return "signup";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult)
    {
       if(bindingResult.hasErrors())
       {
           return "signup";
       }
       else {
           userService.addUser(user);
           return "home";
       }
    }

}
