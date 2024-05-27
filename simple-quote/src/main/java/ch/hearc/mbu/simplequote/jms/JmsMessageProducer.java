/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: JMS Message Producer Interface
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.jms;

import ch.hearc.mbu.simplequote.dto.AuthorDTO;
import ch.hearc.mbu.simplequote.dto.QuoteDTO;

public interface JmsMessageProducer {
    void sendHourlyAnswer(QuoteDTO quoteDTO, String correlationID);
    void sendLastAddedQuote(QuoteDTO quoteDTO);
    void sendLastAddedAuthor(AuthorDTO authorDTO);
}
