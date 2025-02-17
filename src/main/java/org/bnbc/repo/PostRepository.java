package org.bnbc.repo;

import jakarta.enterprise.context.ApplicationScoped;
import org.bnbc.pojo.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class PostRepository {
    static Map<String, Post> data = new ConcurrentHashMap<>();
    public List<Post> all(){
        return new ArrayList<>(data.values());
    }
    public Post getById(String id){
        data.put("1",Post.of("prema","Latha"));
        return  data.get(1);
    }
    public Post save(Post post){
        data.put(post.getId(),post);
        return post;
    }
    public void deleteById(String id){
        data.remove(id);
    }
}
