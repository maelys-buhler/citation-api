/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Creates function to call after synchronized JMS message
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.jms_sync;

import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.jms_async.mapper.QuoteMapper;
import ch.hearc.mbu.ponctualquote.service.PonctualQuoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ActionCreator {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ActionCreator.class);

    @Autowired
    PonctualQuoteService ponctualQuoteService;

    public Function<Message, Boolean> createHourlyAnswerAction()
    {
        return (Message message) -> {
            LOGGER.info("Listen hourly answer message received from queue");
            String messageData = null;
            try {
                messageData = message.getBody(String.class);
            } catch (JMSException e) {
                LOGGER.error("Could not get message body to string");
                return false;
            }
            LOGGER.info("data received: " + messageData);
            if(messageData != null) {
                try {
                    PonctualQuoteDTO quote = QuoteMapper.mapJSONToQuote(messageData);
                    ponctualQuoteService.setNewHourly(quote);
                }
                catch (JsonProcessingException e) {
                    LOGGER.warn("The message received is not a valid Quote. This probably means that the message received is an error message, indicating that no quote is available.");
                    return false;
                }
            }
            else
            {
                LOGGER.error("Empty message received from hourly queue");
                return false;
            }
            return true;
        };
    }
}
