package pl.szczypkowski.blog.Service;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.UserRepo;

import java.time.LocalDate;

@Service
public class UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public void addUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       // user.setRole("ROLE_USER");
        user.setEnable(true);
        userRepo.save(user);
    }

    public Iterable<User>findAll()
    {
        return userRepo.findAll();
    }
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB()
    {
        addUser(new User("Seba","email@gmail.com", "123456",LocalDate.of(1998,5,5),"ROLE_ADMIN",true));
        addUser(new User("Seba2","email2@gmail.com", "123456",LocalDate.of(1998,5,5),"ROLE_USER",true));
        //userRepo.save(new User("Seba","email@gmail.com", "123456",LocalDate.of(1998,5,5),"ROLE_ADMIN",true));
    }


}


