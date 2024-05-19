package ch.hearc.mbu.ponctualquote.remote;

import ch.hearc.mbu.ponctualquote.remote.models.QuoteResponseBody;

import java.util.Optional;

public interface SimpleQuoteRemoteServiceClient {
    Optional<QuoteResponseBody> getRandomQuote();
}
