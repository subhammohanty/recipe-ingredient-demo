package org.hpctech.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class Recipe{

    @Id
    private int id;
    private String name;
    private String description;
    private String imagePath;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_ing_id", referencedColumnName = "id")
    private List<Ingredient> ingredient;

    public Recipe(String name, String description, String imagePath, List<Ingredient> ingredient) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.ingredient = ingredient;
    }

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }
}
