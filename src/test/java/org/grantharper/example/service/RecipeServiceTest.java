package org.grantharper.example.service;

import org.grantharper.example.domain.Recipe;
import org.grantharper.example.dto.RecipeDTO;
import org.grantharper.example.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecipeServiceTest {

    private static final Long TEST_ID = 1L;

    private RecipeService recipeService;
    private RecipeRepository recipeRepository;

    @Before
    public void setup() throws Exception {
        recipeRepository = mock(RecipeRepository.class);
        recipeService = new RecipeService(recipeRepository);
    }

    @Test
    public void givenRecipeIdShouldRetrieveRecipeDTO() {
        when(recipeRepository.findById(TEST_ID)).thenReturn(Optional.of(getSampleRecipe()));

        RecipeDTO recipe = recipeService.getRecipe(TEST_ID);

        assertThat(recipe.getTitle()).isEqualTo("Red Sauce");
        assertThat(recipe.getAuthor()).isEqualTo("Bethanie");
    }

    private Recipe getSampleRecipe() {
        return new Recipe("Red Sauce", "Bethanie");
    }
}