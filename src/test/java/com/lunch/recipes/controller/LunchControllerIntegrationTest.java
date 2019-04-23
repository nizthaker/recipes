package com.lunch.recipes.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LunchControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnHamAndCheeseToastieRecipeWhenSupplyingHamCheeseBreadButterAsIngredients() throws Exception {
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
    public void shouldReturnMultipleRecipesWhenSupplyingButterHamCheeseBaconEggsBakedBeansMushroomsSausageBreadSausageBreadHotdogBunKetchupMustard() throws Exception {}

    @Test
    public void shouldReturnErrorWhenNoIngredientsSupplied() throws Exception {
        mvc.perform(get("/lunch/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotReturnRecipesWhenIngredientIsExpired() throws Exception {
        mvc.perform(get("/lunch/Mushrooms"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundStatusWhenSupplyingNonExistingIngredients() throws Exception {
        mvc.perform(get("/lunch/random,text"))
                .andExpect(status().isNotFound());
    }
}
