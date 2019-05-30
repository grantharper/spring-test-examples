package org.grantharper.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class TranslationDTOJsonTest {

    @Autowired
    private JacksonTester<TranslationDTO> tester;

    @Test
    public void shouldSerialize() throws IOException {
        TranslationDTO translationDTO = new TranslationDTO("Tomato Sauce", "fr");
        assertThat(tester.write(translationDTO)).isEqualToJson(getTranslationRequest());
        assertThat(tester.write(translationDTO))
                .extractingJsonPathStringValue("$.text").isEqualTo("Tomato Sauce");

    }

    @Test
    public void shouldDeserialize() throws IOException {
        TranslationDTO object = tester.read(getTranslationRequest()).getObject();

        assertThat(object).isNotNull();
        assertThat(object.getLang()).isEqualTo("fr");
        assertThat(object.getText()).isEqualTo("Tomato Sauce");
    }

    private InputStream getTranslationRequest() {
        return getClass().getClassLoader().getResourceAsStream("translation-request.json");
    }
}
