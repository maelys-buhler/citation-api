/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Client of synchronized HTTP request
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.remote.impl;

import ch.hearc.mbu.ponctualquote.remote.SimpleQuoteRemoteServiceClient;
import ch.hearc.mbu.ponctualquote.remote.models.QuoteResponseBody;
import com.ctc.wstx.shaded.msv_core.driver.textui.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Optional;

@Service
public class SimpleQuoteRemoteServiceClientImpl implements SimpleQuoteRemoteServiceClient {

    @Autowired
    RestClient simpleQuoteServiceClient;

    @Override
    public Optional<QuoteResponseBody> getRandomQuote() {
        try {
            ResponseEntity<QuoteResponseBody> responseEntity = simpleQuoteServiceClient.get()
                    .uri("/quotes/random")
                    .retrieve()
                    .toEntity(QuoteResponseBody.class);
            return Optional.ofNullable(responseEntity.getBody());
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
