package org.bnbc;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.bnbc.pojo.Post;
import org.bnbc.repo.PostRepository;
import org.jboss.logging.annotations.Pos;

import java.util.logging.Logger;

@ApplicationScoped
public class AppInitializer {

    private final static Logger LOGGER =Logger.getLogger(AppInitializer.class.getName());

    @Inject
    private PostRepository postRepository;

    void onStart(@Observes StartupEvent ev){
        LOGGER.info("The application is starting...");
        Post first = Post.of("Hello Quarkus", "My first post of Quarkus");
        Post second = Post.of("Hello Again, Quarkus", "My second post of Quarkus");
        this.postRepository.save(first);
        this.postRepository.save(second);
    }
    void onStop(@Observes ShutdownEvent shutdownEvent)
    {
        LOGGER.info("The application is stopping...");
    }
}
