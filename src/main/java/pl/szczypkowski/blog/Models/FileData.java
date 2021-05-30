package pl.szczypkowski.blog.Models;


import javax.persistence.*;

@Entity
public class FileData
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_id;
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
        if (name == null || file_id == null) return null;

        return "/post-files/" + file_id + "/" + name;
    }

    public FileData() {
    }

    public Long getFile_id() {
        return file_id;
    }

    public void setFile_id(Long id) {
        this.file_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
