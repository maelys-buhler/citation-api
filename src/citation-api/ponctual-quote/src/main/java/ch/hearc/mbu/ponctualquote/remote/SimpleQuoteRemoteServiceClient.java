/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Interface of Client of synchronized HTTP request
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.remote;

import ch.hearc.mbu.ponctualquote.remote.models.QuoteResponseBody;

import java.util.Optional;

public interface SimpleQuoteRemoteServiceClient {
    Optional<QuoteResponseBody> getRandomQuote();
}
