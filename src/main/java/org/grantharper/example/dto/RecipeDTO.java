package org.grantharper.example.dto;

public class RecipeDTO {

    private final String title;
    private final String author;

    public RecipeDTO(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
