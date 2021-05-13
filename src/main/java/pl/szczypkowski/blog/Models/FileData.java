package pl.szczypkowski.blog.Models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class FileData
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private  String type;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public FileData(String name, String type) {
        this.name = name;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Transient
    public String getPhotosImagePath() {
        if (name == null || id == null) return null;

        return "/post-files/" + id + "/" + name;
    }

    public FileData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
