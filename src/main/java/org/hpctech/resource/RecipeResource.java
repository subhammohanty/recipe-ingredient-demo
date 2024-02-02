package org.hpctech.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hpctech.entity.Recipe;
import org.hpctech.service.RecipeService;

import java.util.List;

@Path("/recipe")
public class RecipeResource {

    @Inject
    private RecipeService recipeService;

    @ConfigProperty(name = "quarkus.application.version")
    String version;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipes() {
        List<Recipe> recipeList = recipeService.getRecipeList();
//        System.out.println(version);
        return Response.ok(recipeList).build();
    }

    @GET
    @Path("/{id}")
    public Response getRecipe(@PathParam("id") int id) {
        Recipe recipeById = recipeService.getRecipeById(Long.valueOf(id));
        return Response.ok(recipeById).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response saveRecipe(@RequestBody Recipe recipe) {
        return Response.ok(recipeService.saveRecipe(recipe)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateRecipe(@PathParam("id") int id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.updateRecipe(id, recipe);
        return Response.ok(recipe1).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRecipe(@PathParam("id") int id) {
        String response = recipeService.deleteRecipe(id);
        return Response.ok(response).build();
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name) {
        Recipe recipeByName = recipeService.getRecipeByName(name);
        return Response.ok(recipeByName).build();

    }


}
