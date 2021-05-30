package pl.szczypkowski.blog.Models;

import javax.persistence.*;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token_id;
    private String token;
    @OneToOne
    private User user;

    public Long getToken_id() {
        return token_id;
    }

    public void setToken_id(Long id) {
        this.token_id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
