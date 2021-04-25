package pl.szczypkowski.blog.Controller;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Service.PostService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {


    private PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(Principal principal, Model model)
    {
        Collection<?extends GrantedAuthority> authorities= SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("authorities",authorities);
        List<Post> postList =(List<Post>) postService.findAll();
        model.addAttribute("posts",postList);
        model.addAttribute("name",principal.getName());

        return "home";
    }
}
