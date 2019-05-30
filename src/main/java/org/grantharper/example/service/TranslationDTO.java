package org.grantharper.example.service;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TranslationDTO {
    private String text;
    private String lang;

    @JsonCreator
    public TranslationDTO(String text, String lang) {
        this.text = text;
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public String getLang() {
        return lang;
    }
}
