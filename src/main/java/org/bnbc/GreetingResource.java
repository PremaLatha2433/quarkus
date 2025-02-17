package org.bnbc;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.bnbc.services.GreetingService;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greetingService.greet();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("greeting/{name}")
    public String hello(@PathParam("name") String name){
        return greetingService.greet(name);
    }
}
