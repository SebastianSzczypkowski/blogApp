package pl.szczypkowski.blog.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.PostRepo;
import pl.szczypkowski.blog.Repos.UserRepo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    private  UserRepo userRepo;
    private PostRepo postRepo;

    public ProfileController(UserRepo userRepo, PostRepo postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String yourProfile(Principal principal, Model model)
    {
        String name =principal.getName();
        Optional<User>userOpitonal =userRepo.findByUsername(name);
        User user=userOpitonal.get();

       ArrayList<Post> postList=(postRepo.findByUser(user));
        Post post = new Post();

        model.addAttribute("currentUser",userOpitonal.get());
        if(!postList.isEmpty())
        {
            model.addAttribute("userPosts",postList.subList(0, postList.size()-1));
        }

        return "profile";
    }
    @RequestMapping(value="/checkProfile/{id}", method = RequestMethod.GET)
    public String profile(@PathVariable("id") long id, Principal principal, Model model)
    {

        User user=userRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user ID:" +id));
        model.addAttribute("userProfile",user);
        return "UserProfile";
    }

}
