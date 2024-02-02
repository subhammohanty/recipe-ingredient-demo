package org.hpctech.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.hpctech.entity.IngredientList;

@ApplicationScoped
public class IngredientListRepository implements PanacheRepository<IngredientList> {
}
