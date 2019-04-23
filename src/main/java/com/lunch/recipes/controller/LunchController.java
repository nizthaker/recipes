package com.lunch.recipes.controller;


import com.lunch.recipes.domain.Recipe;
import com.lunch.recipes.service.LunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LunchController {
    @Autowired
    private LunchService lunchService;

    @GetMapping("/lunch/{ingredients}")
    public @ResponseBody ResponseEntity<List<Recipe>> getRecipesByIngredients(@PathVariable(name = "ingredients") List<String> ingredients) {
        final List<Recipe> recipes = lunchService.getRecipesByIngredients(ingredients);
        if (recipes == null || recipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
}
