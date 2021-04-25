package pl.szczypkowski.blog.Service;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.szczypkowski.blog.Models.Post;
import pl.szczypkowski.blog.Repos.PostRepo;

@Service
public class PostService {

    private PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public void addPost(Post post)
    {
        postRepo.save(post);
    }
    public Iterable<Post>findAll()
    {
        return postRepo.findAll();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB()
    {
        addPost(new Post("Auta","Motoryzacja","Jakiś tekst"));
        addPost(new Post("Programowanie Java","Programowanie","Jakiś tekst"));
    }
}

