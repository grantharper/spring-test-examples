package org.grantharper.example.web.rest;


import org.grantharper.example.dto.RecipeDTO;
import org.grantharper.example.service.RecipeNotFoundException;
import org.grantharper.example.service.RecipeService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({RecipeResource.class})
public class RecipeResourceTest {

    private static final Long TEST_ID = 1L;
    private static final Long NOT_FOUND_ID = 1000L;

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private RecipeService recipeService;

    public RecipeDTO getSampleRecipe() {
        return new RecipeDTO("Strawberry Shortcake", "Grant");
    }

    @Test
    public void givenRequestAllRecipesJsonShouldReturnAllRecipesJson() throws Exception {
        when(recipeService.getAllRecipes()).thenReturn(Stream.of(getSampleRecipe()).collect(Collectors.toList()));
        mockMvc.perform(get("/recipe").accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json("[{'title':'Strawberry Shortcake','author':'Grant'}]"));
    }

    @Test
    public void givenRequestAllRecipesHtmlShouldReturnAllRecipesHtml() throws Exception {
        mockMvc.perform(get("/recipe").accept(MediaType.TEXT_HTML))
               .andExpect(status().isOk())
               .andExpect(content().string("recipe"));
    }

    @Test
    public void givenRequestRecipeJsonShouldReturnRecipeJson() throws Exception {
        when(recipeService.getRecipe(TEST_ID)).thenReturn(getSampleRecipe());

        mockMvc.perform(get("/recipe/" + TEST_ID).accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json("{'title':'Strawberry Shortcake','author':'Grant'}"));
    }

    @Test
    public void givenRequestNoResourceShouldReturn404() throws Exception {
        when(recipeService.getRecipe(NOT_FOUND_ID)).thenThrow(RecipeNotFoundException.class);

        mockMvc.perform(get("/recipe/" + NOT_FOUND_ID).accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }
}
