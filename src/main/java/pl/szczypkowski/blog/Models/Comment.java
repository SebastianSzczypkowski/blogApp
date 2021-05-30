package pl.szczypkowski.blog.Models;


import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comment_id;
    private String text;
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment() {
    }

    public Comment(String text) {
        this.text = text;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long id) {
        this.comment_id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
