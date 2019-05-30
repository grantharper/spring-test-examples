package org.grantharper.example.service;

import org.grantharper.example.domain.Recipe;
import org.grantharper.example.dto.RecipeDTO;
import org.grantharper.example.repository.RecipeRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final TranslationService translationService;

    public RecipeService(RecipeRepository recipeRepository, TranslationService translationService) {
        this.recipeRepository = recipeRepository;
        this.translationService = translationService;
    }

    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll().stream()
                        .map(recipe -> new RecipeDTO(recipe.getTitle(), recipe.getAuthor()))
                        .collect(Collectors.toList());
    }

    public RecipeDTO getRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        return new RecipeDTO(recipe.getTitle(), recipe.getAuthor());
    }

    public RecipeDTO getTranslatedRecipe(Long recipeId, String targetLanguage) {
        RecipeDTO recipe = getRecipe(recipeId);
        String translatedTitle = translationService.translate(recipe.getTitle(), targetLanguage);
        return new RecipeDTO(translatedTitle, recipe.getAuthor());
    }
}
