package pl.szczypkowski.blog.Models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName ="id" )
    private User user;


    public Post(@NotBlank @Size(min = 3, max = 120) String topic, @NotBlank @Size(min = 3, max = 100) String category, @NotBlank String postText) {
        this.topic = topic;
        this.category = category;
        this.postText = postText;

    }

    public Post() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
