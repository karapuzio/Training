package edu.training.fifth.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 20.11.2016.
 */
public class Ingredients {
    private Set<Ingredient> ingredients;

    public Ingredients(){
        ingredients = new HashSet<>();
    }

    public Ingredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void add(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Ingredients ingredient = (Ingredients) object;
        return !(ingredients != null ? !ingredients.equals(ingredient.ingredients) : ingredient.ingredients != null);

    }

    @Override
    public int hashCode() {
        return ingredients != null ? ingredients.hashCode() : 0;
    }
}
