package org.bnbc.pojo;

import java.io.Serializable;
import java.util.UUID;

public class Post implements Serializable {
    String id;
    String title;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Post of(String title , String name){
        Post post = new Post();
        post.setName(name);
        post.setTitle(title);
        post.setId(UUID.randomUUID().toString());
        return post;
    }
}
