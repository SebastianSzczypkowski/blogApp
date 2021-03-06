package pl.szczypkowski.blog.Models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;
    @NotBlank
    @Size(min=3,max=120)
    private String topic;
    @NotBlank
    @Size(min=3,max=100)
    private String category;
    @NotBlank
    @Lob
    private String postText;
   @ManyToOne
   @JoinColumn(name="user_id")
   private User user;
   @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
   private List<FileData> files;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public List<FileData> getFiles() {
        return files;
    }

    public void setFiles(List<FileData> files) {
        this.files = files;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post( String topic,  String category,  String postText, User user) {
        this.topic = topic;
        this.category = category;
        this.postText = postText;
        this.user = user;
    }

    public Post() {
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long id) {
        this.post_id = id;
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
