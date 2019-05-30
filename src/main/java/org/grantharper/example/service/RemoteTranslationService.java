package org.grantharper.example.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteTranslationService implements TranslationService {

    private RestTemplate restTemplate;

    public RemoteTranslationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String translate(String text, String language) {
        TranslationDTO translationDTO = new TranslationDTO();
        translationDTO.text = text;
        translationDTO.lang = language;
        try {
            return restTemplate.postForObject("/translate", translationDTO, TranslationDTO.class).text;
        } catch (HttpStatusCodeException e) {
            throw new TranslationServiceErrorException();
        }
    }

    public static class TranslationDTO {
        public String text;
        public String lang;
    }
}
