package org.grantharper.example.service;

import org.grantharper.example.domain.Recipe;
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
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        return new RecipeDTO(recipe.getTitle(), recipe.getAuthor());
    }
}
