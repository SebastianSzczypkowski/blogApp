package pl.szczypkowski.blog.Service;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Models.Token;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.PostRepo;
import pl.szczypkowski.blog.Repos.TokenRepo;
import pl.szczypkowski.blog.Repos.UserRepo;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private PostRepo postRepo;
    private TokenRepo tokenRepo;
    private MailService mailService;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, PostRepo postRepo, TokenRepo tokenRepo,MailService mailService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.postRepo = postRepo;
        this.tokenRepo = tokenRepo;
        this.mailService=mailService;
    }

    public void addUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole()==null) {
            user.setRole("ROLE_USER");
        }
        user.setEnable(true);
        userRepo.save(user);
       generateVerificationToken(user);
    }

    private void generateVerificationToken(User user) {
        String tokenValue= UUID.randomUUID().toString();
        Token token=new Token();
        token.setToken(tokenValue);
        token.setUser(user);
        tokenRepo.save(token);

        String url="http://localhost:8080/token?value="+tokenValue;
        try
        {
            mailService.sendMail(user.getEmailAddress(),"Potwierdzć swoją rejestracje w chatApp",url,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Iterable<User>findAll()
    {
        return userRepo.findAll();
    }
    /*@EventListener(ApplicationReadyEvent.class)
    public void fillDB()
    {
        addUser(new User("Seba","email@gmail.com", "123456",LocalDate.of(1998,5,5),"ROLE_ADMIN",true));
        addUser(new User("Seba2","email2@gmail.com", "123456",LocalDate.of(1998,5,5),"ROLE_USER",true));

    }
*/

}


