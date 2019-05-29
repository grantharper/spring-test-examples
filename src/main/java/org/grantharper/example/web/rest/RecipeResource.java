package org.grantharper.example.web.rest;

import org.grantharper.example.dto.RecipeDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class RecipeResource {


    @GetMapping(path = "/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeDTO> getAllRecipes() {
        return Stream.of(new RecipeDTO("Strawberry Shortcake", "Grant")).collect(Collectors.toList());
    }

}
