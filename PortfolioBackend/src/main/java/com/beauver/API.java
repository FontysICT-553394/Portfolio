package com.beauver;

import com.beauver.Enums.ItemType;
import com.beauver.Objects.Category;
import com.beauver.Objects.Item;
import com.beauver.Objects.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Comparator;
import java.util.List;

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
    @Path("/item/create")
    public String createItem(String itemJSON) {
        Response response;

        try {
            System.out.println(itemJSON);
            ObjectMapper mapper = new ObjectMapper();
            // Extract categoryName from nested category object
            String categoryName = mapper.readTree(itemJSON).get("category").get("name").asText();

            Category category = Category.find("name", categoryName).firstResult();
            if (category == null) {
                return new Response(400, "Category not found").toJSON();
            }

            String itemName = mapper.readTree(itemJSON).get("name").asText();
            String itemDescription = mapper.readTree(itemJSON).get("description").asText();
            String logoLink = mapper.readTree(itemJSON).get("logoLink").asText();
            String link = mapper.readTree(itemJSON).get("link").asText();
            ItemType itemType = ItemType.valueOf(mapper.readTree(itemJSON).get("itemType").asText());

            Item item = new Item();
            item.name = itemName;
            item.description = itemDescription;
            item.logoLink = logoLink;
            item.link = link;
            item.itemType = itemType;
            item.category = category;

            item.persist();
            return new Response(200, "OK").toJSON();
        } catch (Exception e) {
            return new Response(400, "Invalid item data: " + e.getMessage()).toJSON();
        }
    }

    @GET
    @Path("/item/getAll")
    public String getItems() {
        return new Response(200, "Items retrieved successfully", Item.listAll()).toJSON();
    }

    @GET
    @Path("/category/getAll")
    public String getCategories() {
        List<Category> categories = Category.listAll();
        categories.sort(Comparator.comparing(Category::getOrderId, Comparator.nullsLast(Comparator.naturalOrder())));
        return new Response<>(200, "Categories retrieved successfully", categories).toJSON();
    }

    @POST
    @Transactional
    @Path("/category/create")
    public String createCategory(Category category) {
        category.persist();
        return new Response<>(200, "Category created successfully", category).toJSON();
    }

    @DELETE
    @Transactional
    @Path("/item/delete/{id}")
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
