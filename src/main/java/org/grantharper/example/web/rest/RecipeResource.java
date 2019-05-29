package org.grantharper.example.web.rest;

import org.grantharper.example.dto.RecipeDTO;
import org.grantharper.example.service.RecipeNotFoundException;
import org.grantharper.example.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundRecipe(RecipeNotFoundException ex) {
        //Spring handles the response here so the method is empty
    }

}
