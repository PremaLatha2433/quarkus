package org.bnbc.controller;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.container.ResourceContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.bnbc.pojo.Comment;
import org.bnbc.repo.CommentRepository;
import org.jboss.logging.Logger;

import static jakarta.ws.rs.core.Response.created;
import static jakarta.ws.rs.core.Response.ok;

@Unremovable
//@RegisterForReflect
@RequestScoped
public class CommentResource {
    private final static Logger LOGGER = Logger.getLogger(CommentResource.class.getName());
    private final CommentRepository comments;
    @Context
    UriInfo uriInfo;
    @Context
    ResourceContext resourceContext;
    @PathParam("id")
    String postId;
    @Inject
    public CommentResource(CommentRepository commentRepository) {
        this.comments = commentRepository;
    }
    @GET
    public Response getAllComments() {
        return ok(this.comments.allByPostId(this.postId)).build();
    }
    @POST
    public Response saveComment(Comment commentForm) {
        Comment saved = this.comments.save(Comment.of(this.postId, commentForm.getContent()));
        return created(
                uriInfo.getBaseUriBuilder()
                        .path("/posts/{id}/comments/{commentId}")
                        .build(this.postId, saved.getId())
        ).build();
    }
}
