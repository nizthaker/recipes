package com.lunch.recipes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe implements Comparable {
    private String title;
    private List<Ingredient> ingredients;

    @Override
    public int compareTo(Object o) {
        Recipe recipe = (Recipe) o;

        Collections.sort(ingredients);
        Collections.sort(recipe.getIngredients());

        if(ingredients.get(0).getBestBefore().before(recipe.getIngredients().get(0).getBestBefore())) {
            return 1;
        } else if (ingredients.get(0).getBestBefore().after(recipe.getIngredients().get(0).getBestBefore())) {
            return -1;
        } else {
            return 0;
        }

    }
}
