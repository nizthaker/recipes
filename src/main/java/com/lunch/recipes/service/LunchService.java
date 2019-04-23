package com.lunch.recipes.service;


import com.lunch.recipes.domain.Recipe;
import com.lunch.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class LunchService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
       //"http://www.mocky.io/v2/5c9c33173600009f4ad96f2f" All Ingredients in provided URL are expired,
       // needed to set current date in past
       LocalDate localDate = LocalDateTime.of(2019, 4, 6, 0, 0).toLocalDate();
       Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
       //Date currentDate =  new Date();

        List<Recipe> recipes = recipeRepository.getAllRecipes(ingredients, currentDate);

        return recipes;
    }
}
