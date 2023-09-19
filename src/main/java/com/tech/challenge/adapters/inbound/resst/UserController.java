package com.tech.challenge.adapters.inbound.resst;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import com.tech.challenge.adapters.inbound.application.UserDTO;
import com.tech.challenge.adapters.inbound.application.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public List<UserDTO> listUsers() {
        return userService.getAllUsers();
    }

    @POST
    public Response createUser(UserDTO userDTO) {
        userService.createUser(userDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public UserDTO getUser(@PathParam("id") Long id) {
        return userService.getUserById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);;
        return Response.noContent().build();
    }
}
