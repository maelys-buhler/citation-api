package ch.hearc.mbu.simplequote.jms;

import ch.hearc.mbu.simplequote.dto.AuthorDTO;
import ch.hearc.mbu.simplequote.dto.QuoteDTO;

public interface JmsMessageProducer {
    void sendHourlyAnswer(QuoteDTO quoteDTO, String correlationID);
    void sendLastAddedQuote(QuoteDTO quoteDTO);
    void sendLastAddedAuthor(AuthorDTO authorDTO);
}
