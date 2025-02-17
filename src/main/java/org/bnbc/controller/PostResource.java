package org.bnbc.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ResourceContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.bnbc.exception.PostNotFoundException;
import org.bnbc.pojo.Post;
import org.bnbc.repo.PostRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import static jakarta.ws.rs.core.Response.*;

@Path("/posts")
@RequestScoped
public class PostResource {
    private final static Logger LOGGER = Logger.getLogger(PostResource.class.getName());
    private final PostRepository posts;
    @Context
    ResourceContext resourceContext;
    @Context
    UriInfo uriInfo;
    @Inject
    public PostResource(PostRepository posts) {
        this.posts = posts;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get all Posts",
            description = "Get all posts"
    )
    @APIResponse(
            responseCode = "200",
            name = "Post list",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = Post.class
                    )
            )
    )
    public Response getAllPosts() {
        return ok(this.posts.all()).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Save Posts",
            description = "Save posts"
    )
    @APIResponse(
            responseCode = "200",
            name = "Save Posts",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = Post.class
                    )
            )
    )
    public Response savePost(@Valid Post post) {
        Post saved = this.posts.save(Post.of(post.getTitle(), post.getName()));
        return created(
                uriInfo.getBaseUriBuilder()
                        .path("/posts/{id}")
                        .build(saved.getId())
        ).build();
    }
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("id") final String id) {
        Post post = this.posts.getById(id);
        if(post == null){
            throw new PostNotFoundException("Post Not Found with this id"+id);
        }
        return ok(post).build();
    }
    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePost(@PathParam("id") final String id, @Valid Post post) {
        Post existed = this.posts.getById(id);
        existed.setTitle(post.getTitle());
        existed.setName(post.getName());
        Post saved = this.posts.save(existed);
        return noContent().build();
    }
    @Path("{id}")
    @DELETE
    public Response deletePost(@PathParam("id") final String id) {
        this.posts.deleteById(id);
        return noContent().build();
    }
}
