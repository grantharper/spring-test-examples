package org.grantharper.example.web.rest;

import org.grantharper.example.dto.RecipeDTO;
import org.grantharper.example.service.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RecipeResource {


    private final RecipeService recipeService;

    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(path = "/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping(path = "/recipe", produces = MediaType.TEXT_HTML_VALUE)
    public String getAllRecipesPage(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "recipe";
    }

    @GetMapping(path = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecipeDTO getRecipe(@PathVariable Long id) {
        return recipeService.getRecipe(id);
    }

}
