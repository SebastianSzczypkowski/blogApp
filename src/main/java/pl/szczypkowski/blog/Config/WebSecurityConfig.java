package pl.szczypkowski.blog.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.szczypkowski.blog.Service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Bean
    public PasswordEncoder getPasswordEncoder()
        {
            return new BCryptPasswordEncoder();
        }
        private UserDetailsServiceImpl userDetailsService;

        public WebSecurityConfig(UserDetailsServiceImpl userDetailsService)
        {
            this.userDetailsService=userDetailsService;
        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable();
            http.headers().disable();
            http.authorizeRequests().
                    antMatchers("/signup").permitAll()
                    .antMatchers("/home").authenticated()
                    .antMatchers("/adminPanel").hasRole("ADMIN")
                    .antMatchers("/addPost").authenticated()
                    .antMatchers("/edit").hasRole("ADMIN")
                    .antMatchers("/addFile").authenticated()
                    .antMatchers("/uploadFile").authenticated()
                    .antMatchers("/editUser").hasRole("ADMIN")
                    .antMatchers("/editPost").hasRole("ADMIN")
                    .antMatchers("/updateUser").hasRole("ADMIN")
                    .antMatchers("/updatePost").hasRole("ADMIN")
                    .antMatchers("/deleteUser").hasRole("ADMIN")
                    .antMatchers("/deletePost").hasRole("ADMIN")
                    .antMatchers("/addYourComment").authenticated()
                    .antMatchers("/addPost").authenticated()
                    .antMatchers("/addYourPost").authenticated()
                    .antMatchers("/profile").authenticated()
                    .antMatchers("/checkProfile").authenticated()
                    .and().formLogin().defaultSuccessUrl("/home");
    }
}
