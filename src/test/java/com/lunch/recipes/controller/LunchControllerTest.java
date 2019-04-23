package com.lunch.recipes.controller;


import com.lunch.recipes.domain.Ingredient;
import com.lunch.recipes.domain.Recipe;
import com.lunch.recipes.service.LunchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LunchControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private LunchService lunchService;

    @Test
    public void shouldReturnHamAndCheeseToastieRecipeWhenSupplyingHamCheeseBreadButterAsIngredients() throws Exception {

        List<Ingredient> ingredientList = Arrays.asList(
                new Ingredient("Ham",convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate())),
                new Ingredient("Cheese",convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate())),
                new Ingredient("Bread",convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate())),
                new Ingredient("Butter",convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate()))
        );

        List<String> ingredientsAsString = Arrays.asList(
                "Ham",
                "Cheese",
                "Bread",
                "Butter"
        );
        List<Recipe> recipes = Arrays.asList(new Recipe("Ham and Cheese Toastie", ingredientList));

        when(lunchService.getRecipesByIngredients(ingredientsAsString)).thenReturn(recipes);

        mvc.perform(get("/lunch/Ham,Cheese,Bread,Butter"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Ham and Cheese Toastie"))
                .andExpect(jsonPath("$[0].ingredients[0].title").value("Ham"))
                .andExpect(jsonPath("$[0].ingredients[0].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[0].ingredients[0].use-by").value("2019-04-12"))

                .andExpect(jsonPath("$[0].ingredients[1].title").value("Cheese"))
                .andExpect(jsonPath("$[0].ingredients[1].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[0].ingredients[1].use-by").value("2019-04-12"))

                .andExpect(jsonPath("$[0].ingredients[2].title").value("Bread"))
                .andExpect(jsonPath("$[0].ingredients[2].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[0].ingredients[2].use-by").value("2019-04-12"))

                .andExpect(jsonPath("$[0].ingredients[3].title").value("Butter"))
                .andExpect(jsonPath("$[0].ingredients[3].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[0].ingredients[3].use-by").value("2019-04-12"));

    }

    @Test
    public void shouldReturnMultipleRecipesWhenSupplyingButterHamCheeseBaconEggsMushroomsSausageBreadSausageBreadHotdogBunKetchupMustard() throws Exception {

       Ingredient Ham =  new Ingredient("Ham",convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate()));
       Ingredient Cheese =  new Ingredient("Cheese",convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate()));
       Ingredient Bread=  new Ingredient("Bread",convertDate(LocalDateTime.of(2019, 1, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate()));
       Ingredient Butter = new Ingredient("Butter",convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate()));
       Ingredient Bacon = new Ingredient("Bacon",convertDate(LocalDateTime.of(2019, 4, 2, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()));
       Ingredient Eggs =  new Ingredient("Eggs",convertDate(LocalDateTime.of(2019, 4, 2, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()));
       Ingredient Mushrooms = new Ingredient("Mushrooms",convertDate(LocalDateTime.of(2019, 3, 23, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 3, 26, 0, 0).toLocalDate()));
       Ingredient Sausage =  new Ingredient("Sausage",convertDate(LocalDateTime.of(2019, 4, 2, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()));
       Ingredient HotdogBun = new Ingredient("Hotdog Bun",convertDate(LocalDateTime.of(2019, 3, 23, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()));
       Ingredient Ketchup =  new Ingredient("Ketchup",convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate()));
       Ingredient Mustard = new Ingredient("Mustard",convertDate(LocalDateTime.of(2019, 4, 7, 0, 0).toLocalDate()), convertDate(LocalDateTime.of(2019, 4, 12, 0, 0).toLocalDate()));


        List<String> ingredientsAsString = Arrays.asList(
                "Ham",
                "Cheese",
                "Bread",
                "Butter",
                "Bacon",
                "Eggs",
                "Mushrooms",
                "Sausage",
                "Hotdog Bun",
                "Ketchup",
                "Mustard"

        );

        List<Recipe> recipes = Arrays.asList(
                new Recipe("Ham and Cheese Toastie", Arrays.asList(Ham, Cheese, Bread, Butter)),
                new Recipe("Hotdog", Arrays.asList(HotdogBun,Sausage,Ketchup,Mustard)),
                new Recipe("Fry-up", Arrays.asList(Bacon,Eggs,Mushrooms))
        );

       when(lunchService.getRecipesByIngredients(ingredientsAsString)).thenReturn(recipes);

            mvc.perform(get("/lunch/Hotdog Bun,Sausage,Ketchup,Mustard,Butter,Ham,Cheese,Bacon,Eggs,Mushrooms,Sausage,Bread"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(2)))
                .andExpect(jsonPath("$[0].title").value("Ham and Cheese Toastie"))
                .andExpect(jsonPath("$[0].ingredients[0].title").value("Ham"))
                .andExpect(jsonPath("$[0].ingredients[0].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[0].ingredients[0].use-by").value("2019-04-12"))

                .andExpect(jsonPath("$[0].ingredients[1].title").value("Cheese"))
                .andExpect(jsonPath("$[0].ingredients[1].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[0].ingredients[1].use-by").value("2019-04-12"))

                .andExpect(jsonPath("$[0].ingredients[2].title").value("Bread"))
                .andExpect(jsonPath("$[0].ingredients[2].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[0].ingredients[2].use-by").value("2019-04-12"))

                .andExpect(jsonPath("$[0].ingredients[3].title").value("Butter"))
                .andExpect(jsonPath("$[0].ingredients[3].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[0].ingredients[3].use-by").value("2019-04-12"))

                .andExpect(jsonPath("$[1].title").value("Hotdog"))
                .andExpect(jsonPath("$[1].ingredients[0].title").value("Hotdog Bun"))
                .andExpect(jsonPath("$[1].ingredients[0].best-before").value("2019-03-23"))
                .andExpect(jsonPath("$[1].ingredients[0].use-by").value("2019-04-07"))

                .andExpect(jsonPath("$[1].ingredients[1].title").value("Sausage"))
                .andExpect(jsonPath("$[1].ingredients[1].best-before").value("2019-04-02"))
                .andExpect(jsonPath("$[1].ingredients[1].use-by").value("2019-04-07"))

                .andExpect(jsonPath("$[1].ingredients[2].title").value("Ketchup"))
                .andExpect(jsonPath("$[1].ingredients[2].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[1].ingredients[2].use-by").value("2019-04-12"))

                .andExpect(jsonPath("$[1].ingredients[3].title").value("Mustard"))
                .andExpect(jsonPath("$[1].ingredients[3].best-before").value("2019-04-07"))
                .andExpect(jsonPath("$[1].ingredients[3].use-by").value("2019-04-12"));
    }


    @Test
    public void shouldReturnErrorWhenNoIngredientsSupplied() throws Exception {
        mvc.perform(get("/lunch/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundStatusWhenSupplyingNonExistingIngredients() throws Exception {
        mvc.perform(get("/lunch/random,text"))
                .andExpect(status().isNotFound());
    }

    private Date convertDate(LocalDate localDate) {
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return currentDate;
    }

}
