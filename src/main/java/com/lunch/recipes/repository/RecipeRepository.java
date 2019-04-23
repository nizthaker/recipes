package com.lunch.recipes.repository;

import com.lunch.recipes.domain.Ingredient;
import com.lunch.recipes.domain.Recipe;
import com.lunch.recipes.domain.RecipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RecipeRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IngredientRepository ingredientRepository;

    private final String RECIPE_URL = "http://www.mocky.io/v2/5c85f7a1340000e50f89bd6c";


    //returns the list of filtered recipes
    public List<Recipe> getAllRecipes(List<String> inputIngredients, Date currentDate) {
        List<Ingredient> ingredients = convertToIngredients(inputIngredients);
        List<Recipe> recipes = getRecipesFromURI();
        recipes = recipes
                .stream()
                .filter(r -> isMatchedRecipe(r,ingredients))
                .filter(r -> !hasExpiredIngredient(r, currentDate))
                .collect(Collectors.toList());
        return recipes;
    }

    //convert Map String to Ingredient.
    private List<Ingredient> convertToIngredients(List<String> ingredients) {
        return ingredients.stream().map(
                ingredientRepository::getIngredientByTitle)
                .collect(Collectors.toList());
    }

    //get recipes from url.
    private List<Recipe>  getRecipesFromURI() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(RECIPE_URL);

        List<Recipe> recipes = restTemplate.getForObject(uriBuilder.toUriString(), RecipeResponse.class).getRecipes();
        recipes = updateIngredientDates(recipes);
        return recipes;
    }

    //getting used-by and best-before dates from ingredients.
    private List<Recipe> updateIngredientDates(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient ingredientObj = ingredientRepository.getIngredientByTitle(ingredient.getTitle());
                if (ingredientObj != null) {
                    ingredient.setUseBy(ingredientObj.getUseBy());
                    ingredient.setBestBefore(ingredientObj.getBestBefore());
                }
            }
        }
        return recipes;
    }

    //checks if ingredients are matching to recipe.
    private boolean isMatchedRecipe(Recipe recipes, List<Ingredient> ingredients) {
        return ingredients.size() > 0 ? ingredients.containsAll(recipes.getIngredients()) : false;
    }

    //checks if ingredient is expired or not.
    private boolean hasExpiredIngredient(Recipe recipe, Date currentDate) {
        if (recipe.getIngredients() == null && recipe.getIngredients().size() == 0){
            return false;
        }
        long count = recipe.getIngredients()
                .stream()
                .filter(i -> i != null && i.getUseBy() != null)
                .filter(i ->currentDate.after(i.getUseBy()))
                .count();
        return count > 0;
    }
}
