package pl.szczypkowski.blog.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.security.auth.Subject;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts;
    @NotBlank(message = "Musisz podać swoje imie")
    @Size(min=3,max=25,message = "Twoje imie musi składać się conajmniej z 3 znaków oraz musi mieć ich niewięcej niż 25")
    //@Transient
    private String username;
    @NotBlank(message = "Musisz podać adres e-mail")
    @Email(message = "Podaj prawdziwy adres e-mail")
    private String emailAddress;
    @NotBlank
    //@Size(min=6,max=32,message = "Hasło musi składać się z conajmniej 6 znaków oraz nie może być ich więcej niż 32")
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String role;
    private String rank;
    private boolean isEnable;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User(@NotBlank(message = "Musisz podać swoje imie") @Size(min = 3, max = 25, message = "Twoje imie musi składać się conajmniej z 3 znaków oraz musi mieć ich niewięcej niż 25") String username, @NotBlank(message = "Musisz podać adres e-mail") @Email(message = "Podaj prawdziwy adres e-mail") String emailAddress, @NotBlank String password, LocalDate birthday) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.birthday = birthday;
    }
    public User(@NotBlank(message = "Musisz podać swoje imie") @Size(min = 3, max = 25, message = "Twoje imie musi składać się conajmniej z 3 znaków oraz musi mieć ich niewięcej niż 25") String username, @NotBlank(message = "Musisz podać adres e-mail") @Email(message = "Podaj prawdziwy adres e-mail") String emailAddress, @NotBlank String password, LocalDate birthday, String role, boolean isEnable) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
        this.isEnable = isEnable;
    }
    public User() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", birthday=" + birthday +
                ", role='" + role + '\'' +
                '}';
    }
}
