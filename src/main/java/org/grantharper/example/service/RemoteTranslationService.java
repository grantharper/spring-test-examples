package org.grantharper.example.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
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
        TranslationDTO translationDTO = new TranslationDTO();
        translationDTO.text = text;
        translationDTO.lang = language;
        ResponseEntity<TranslationDTO> response = restTemplate.postForEntity("/translate", translationDTO, TranslationDTO.class);
        return response.getBody().text;
    }

    public static class TranslationDTO {
        public String text;
        public String lang;
    }
}
