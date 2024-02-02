package org.hpctech.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hpctech.entity.IngredientList;
import org.hpctech.repository.IngredientListRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/ingredientList")
public class IngredientResource {

    @Inject
    private IngredientListRepository ingredientListRepository;

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<IngredientList> getByName(@PathParam("name") String name){
        List<IngredientList> resultList = ingredientListRepository.list("recipeName", name);
        return resultList;
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<IngredientList> getAll(){
        List<IngredientList> findAll = ingredientListRepository.listAll();

        return findAll.stream()
                .collect(Collectors.groupingBy(IngredientList::getName,
                        Collectors.maxBy(Comparator.comparing(IngredientList::getId))))
                .values()
                .stream()
                .map(opt -> opt.orElse(null))
                .collect(Collectors.toList());
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public String save(@RequestBody IngredientList ingredientList){
        ingredientListRepository.persist(ingredientList);
        return "Saved!!";
    }
}
