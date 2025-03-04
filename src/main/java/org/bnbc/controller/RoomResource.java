package org.bnbc.controller;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bnbc.entities.Room;
import org.bnbc.pojo.Post;
import org.bnbc.services.RoomService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    private final RoomService roomService;

    public RoomResource(RoomService roomService) {
        this.roomService = roomService;
    }

    @GET
    public List<Room> getAllBooks() {
        return Room.listAll();
    }

    @POST
    public Response addBook(Room room) {
         room.persist();
         return Response.status(Response.Status.CREATED).entity(room.isPersistent()).build();
    }
    @GET
    @Operation(
            summary = "Get all Rooms By id and name",
            description = "Get all rooms"
    )
    @APIResponse(
            responseCode = "200",
            name = "Rooms list",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = Post.class
                    )
            )
    )
    public Response findRoomByIdAndName(@PathParam("id") int id,@PathParam("name") String name)
    {
      return Response.ok(roomService.findRoomByIdAndName(id,name)).build();
    }
    @GET
    @Operation(
            summary = "Get all Rooms",
            description = "Get all rooms"
    )
    @APIResponse(
            responseCode = "200",
            name = "Rooms list",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = Post.class
                    )
            )
    )
    public Response findAllRooms(){
        return Response.ok(roomService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Room get(Long id) {
        return roomService.findById(id);
    }
    @PUT
    @Path("/{id}")
    @Transactional
    public Room update(Long id, Room room) {
        Room entity = Room.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.setName(room.getName());
        entity.setRoomNumber(room.getRoomNumber());
        entity.setBedInfo(room.getBedInfo());
        room.persist();
        return entity;
    }
    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Room entity = Room.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }
    @GET
    @Path("/search/{name}")
    public Room search(String name) {
        return roomService.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return Room.count();
    }


}
