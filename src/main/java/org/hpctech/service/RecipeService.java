package org.hpctech.service;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hpctech.entity.Recipe;
import org.hpctech.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class RecipeService {

    @Inject
    private RecipeRepository recipeRepository;

    public List<Recipe> getRecipeList(){
//        List<Recipe> recipeList = recipeRepository.listAll().stream().sorted((r1,r2)-> r1.getId() > r2.getId() ? 1 : -1).collect(Collectors.toList());
        List<Recipe> recipeList = recipeRepository.listAll(Sort.ascending("id"));
        return recipeList;
    }

    public String saveRecipe(Recipe recipe){
        List<Recipe> all = recipeRepository.listAll();
        Recipe recipeById = getRecipeById(Long.valueOf(recipe.getId()));
        if(recipeById != null){
            recipe.setId(recipeById.getId()+1);
        }
        for(Recipe rec: all){
            if(rec.getName().equalsIgnoreCase(recipe.getName())){
                recipe.setId(rec.getId());
            }
        }
        recipeRepository.persist(recipe);
        return "Saved !!";
    }

    public Recipe getRecipeById(Long id) {
        Optional<Recipe> byId = recipeRepository.findByIdOptional(id);
        if(byId.isPresent()){
            return byId.get();
        }else {
            return  null;
        }
    }

    public Recipe updateRecipe(int id, Recipe recipe){
        Optional<Recipe> findById = recipeRepository.findByIdOptional(Long.valueOf(id));
        if(findById.isPresent()){
//            findById.get().setId(recipe.getId());
            findById.get().setName(recipe.getName());
            findById.get().setImagePath(recipe.getImagePath());
            findById.get().setDescription(recipe.getDescription());
            findById.get().setIngredient(recipe.getIngredient());
            recipeRepository.persist(findById.get());
            return findById.get();
        }else{
            return null;
        }
    }

    public String deleteRecipe(int id){
        recipeRepository.deleteById(Long.valueOf(id));
        return "Deleted !!!";
    }

    public Recipe getRecipeByName(String name){
        Recipe recipe = recipeRepository.listAll().stream().filter(rec ->
                rec.getName().equalsIgnoreCase(name)).findFirst().get();
        return recipe;
    }
}

