package org.grantharper.example.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteTranslationService implements TranslationService {

    private RestTemplate restTemplate;
    private TranslationServiceProperties translationServiceProperties;

    public RemoteTranslationService(RestTemplateBuilder restTemplateBuilder,
                                    TranslationServiceProperties properties) {
        this.restTemplate = restTemplateBuilder.rootUri(properties.getRootUrl()).build();
        this.translationServiceProperties = properties;
    }

    @Override
    public String translate(String text, String language) {
        TranslationDTO translationDTO = new TranslationDTO(text, language);
        try {
            return restTemplate.postForObject("/translate", translationDTO, TranslationDTO.class).getText();
        } catch (HttpStatusCodeException e) {
            throw new TranslationServiceErrorException();
        }
    }

}
