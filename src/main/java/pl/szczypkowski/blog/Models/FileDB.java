package pl.szczypkowski.blog.Models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class FileDB
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private byte[] data;

    public FileDB(String name,  byte[] data) {
        super();
        this.name = name;
        this.data = data;
    }

    public FileDB() {
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


    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
