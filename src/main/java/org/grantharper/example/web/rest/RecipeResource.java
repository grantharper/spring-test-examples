package org.grantharper.example.web.rest;

import org.grantharper.example.dto.RecipeDTO;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class RecipeResource {


    @GetMapping(path = "/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeDTO> getAllRecipes() {
        return getListOfSampleRecipes();
    }

    private List<RecipeDTO> getListOfSampleRecipes() {
        return Stream.of(getSampleRecipe()).collect(Collectors.toList());
    }

    @GetMapping(path = "/recipe", produces = MediaType.TEXT_HTML_VALUE)
    public String getAllRecipesPage(Model model) {
        model.addAttribute("recipes", getListOfSampleRecipes());
        return "recipe";
    }


    private RecipeDTO getSampleRecipe() {
        return new RecipeDTO("Strawberry Shortcake", "Grant");
    }

}
