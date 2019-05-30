package org.grantharper.example.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest({RemoteTranslationService.class})
public class RemoteTranslationServiceTest {

    @Autowired
    private RemoteTranslationService remoteTranslationService;

    @Autowired
    private MockRestServiceServer server;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenTranslationRequestShouldReturnTranslation() {
        server.expect(requestTo("/translate"))
              .andRespond(withSuccess(getClassPathResource("translation.json"), MediaType.APPLICATION_JSON));

        String translate = remoteTranslationService.translate("Tomato Sauce", "fr");

        assertThat(translate).isEqualTo("Sauce Tomate");
    }

    @Test
    public void givenServiceIsDownShouldThrowException() {
        server.expect(requestTo("/translate"))
              .andRespond(withServerError());

        thrown.expect(TranslationServiceErrorException.class);
        remoteTranslationService.translate("Tomato Sauce", "fr");
    }

    private ClassPathResource getClassPathResource(String path) {
        return new ClassPathResource(path, getClass().getClassLoader());
    }
}
