package pl.szczypkowski.blog.Controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szczypkowski.blog.Models.Token;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.TokenRepo;
import pl.szczypkowski.blog.Repos.UserRepo;
import pl.szczypkowski.blog.Service.UserService;

import java.security.Principal;
import java.util.Collection;

@Controller
public class SignUpController {

    private UserService userService;
    private TokenRepo tokenRepo;
    private UserRepo userRepo;

    public SignUpController(UserService userService, TokenRepo tokenRepo, UserRepo userRepo) {
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.userRepo = userRepo;
    }



    @GetMapping("/sign-up")
    public String signUP(Model model)
    {
        model.addAttribute("user",new User());
        return "sign-up";
    }
    @PostMapping("/register")
    public String register(User User)
    {
        userService.addUser(User);
        return "sign-up";
    }
    @GetMapping("/token")
    public String signUP(@RequestParam String value)
    {
        Token byValue=tokenRepo.findByToken(value);
        User user=byValue.getUser();
        user.setEnable(true);
        userRepo.save(user);
        return "home";
    }
}
