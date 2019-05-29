package org.grantharper.example.service;

import org.grantharper.example.domain.Recipe;
import org.grantharper.example.dto.RecipeDTO;
import org.grantharper.example.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecipeServiceTest {

    private static final Long TEST_ID = 1L;

    private RecipeService recipeService;
    private RecipeRepository recipeRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    @Test
    public void givenBadRecipeIdShouldThrowException() throws Exception {
        when(recipeRepository.findById(TEST_ID)).thenReturn(Optional.empty());
        expectedException.expect(RecipeNotFoundException.class);
        recipeService.getRecipe(TEST_ID);
    }

    private Recipe getSampleRecipe() {
        return new Recipe("Red Sauce", "Bethanie");
    }
}