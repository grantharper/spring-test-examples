package org.grantharper.example.web.rest;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RecipeResourceTest {

    @Autowired
    MockMvc mockMvc;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenRequestAllRecipesJsonShouldReturnAllRecipesJson() throws Exception {
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
}
