package ch.hearc.mbu.simplequote.jms;

import ch.hearc.mbu.simplequote.dto.QuoteDTO;

public interface JmsMessageProducer {
    void sendHourlyAnswer(QuoteDTO quoteDTO);
    void sendLastAddedQuote(QuoteDTO quoteDTO);
}
