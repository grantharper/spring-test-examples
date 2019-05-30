package org.grantharper.example.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TranslationDTO {

    private final String text;
    private final String lang;

    @JsonCreator
    public TranslationDTO(@JsonProperty("text") String text,
                          @JsonProperty("lang") String lang) {
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
