package org.grantharper.example.service;

import org.grantharper.example.dto.RecipeDTO;
import org.grantharper.example.repository.RecipeRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDTO> getAllRecipes() {
        return new ArrayList<>();
    }

    public RecipeDTO getRecipe(Long recipeId) {
        return null;
    }
}
