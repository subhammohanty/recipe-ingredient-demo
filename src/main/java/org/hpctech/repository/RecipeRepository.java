package org.hpctech.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.hpctech.entity.Recipe;

@ApplicationScoped
public class RecipeRepository implements PanacheRepository<Recipe> {
}
