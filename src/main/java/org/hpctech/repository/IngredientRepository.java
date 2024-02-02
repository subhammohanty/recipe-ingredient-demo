package org.hpctech.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.hpctech.entity.Ingredient;

@ApplicationScoped
public class IngredientRepository implements PanacheRepository<Ingredient> {
}
