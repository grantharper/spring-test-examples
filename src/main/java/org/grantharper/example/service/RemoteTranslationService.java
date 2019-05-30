package org.grantharper.example.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteTranslationService implements TranslationService {

    private RestTemplate restTemplate;

    public RemoteTranslationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String translate(String text, String language) {
        return null;
    }
}
