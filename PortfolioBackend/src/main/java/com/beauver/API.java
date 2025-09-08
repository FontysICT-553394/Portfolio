package com.beauver;

import com.beauver.Objects.Item;
import com.beauver.Objects.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class API {

    @GET
    @Path("/")
    public String active(){
        return new Response(200, "OK").toJSON();
    }

    @POST
    @Transactional
    @Path("/create")
    public String createItem(Item item) {
        System.out.println("Received item: " + item);
        item.persist();
        System.out.println("Item persisted successfully.");
        return new Response(200, "Item created successfully").toJSON();
    }

    @GET
    @Path("/items")
    public String getItems() {
        return new Response(200, "Items retrieved successfully", Item.listAll()).toJSON();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public String deleteItem(@PathParam("id") Long id) {
        Item item = Item.findById(id);
        if (item == null) {
            return new Response(404, "Item not found").toJSON();
        } else {
            item.delete();
            return new Response(200, "Item deleted successfully").toJSON();
        }
    }

}
