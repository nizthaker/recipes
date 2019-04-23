package com.lunch.recipes.repository;

import com.lunch.recipes.domain.Ingredient;
import com.lunch.recipes.domain.IngredientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IngredientRepository {

    @Autowired
    private RestTemplate restTemplate;

    private final String INGREDIENT_URL = "http://www.mocky.io/v2/5c9c33173600009f4ad96f2f";

    public List<Ingredient> getAllIngredients() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(INGREDIENT_URL);

        List<Ingredient> ingredients = restTemplate.getForObject(uriBuilder.toUriString(), IngredientResponse.class).getIngredients();
        return ingredients
                .stream()
                .filter(i-> i.getTitle()!= null && i.getBestBefore()!=null && i.getUseBy()!=null)
                .collect(Collectors.toList());
    }

    public Ingredient getIngredientByTitle(String title) {
        Ingredient ingredient1 = getAllIngredients()
                .stream()
                .filter(ingredient -> ingredient.getTitle().equals(title))
                .findFirst()
                .orElse(null);
        return ingredient1;
    }
}
