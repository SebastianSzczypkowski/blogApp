package pl.szczypkowski.blog.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.szczypkowski.blog.Models.User;
import pl.szczypkowski.blog.Repos.UserRepo;

import javax.persistence.Access;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo userRepo;
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(s).get();
        if(user==null)
        {
            throw new UsernameNotFoundException("Could not find user with that name");
        }else {
            return userRepo.findByUsername(s).get();
        }
    }
}
