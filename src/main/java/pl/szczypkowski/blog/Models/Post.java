package pl.szczypkowski.blog.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Entity

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min=3,max=120)
    private String topic;
    @NotBlank
    @Size(min=3,max=100)
    private String category;
    @NotBlank
    private String postText;
   @ManyToOne
   @JoinColumn(name="user_id")
   private User user;
   @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
   private List<FileDB> files;
    public User getUser() {
        return user;
    }

    public List<FileDB> getFiles() {
        return files;
    }

    public void setFiles(List<FileDB> files) {
        this.files = files;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Post(@NotBlank @Size(min = 3, max = 120) String topic, @NotBlank @Size(min = 3, max = 100) String category, @NotBlank String postText, User user) {
        this.topic = topic;
        this.category = category;
        this.postText = postText;
        this.user = user;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}
