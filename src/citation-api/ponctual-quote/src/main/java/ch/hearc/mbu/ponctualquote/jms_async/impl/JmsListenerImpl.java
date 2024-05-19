package ch.hearc.mbu.ponctualquote.jms_async.impl;

import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.jms_async.JmsMessageListener;
import ch.hearc.mbu.ponctualquote.jms_async.mapper.QuoteMapper;
import ch.hearc.mbu.ponctualquote.service.PonctualQuoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;

@Component
public class JmsListenerImpl implements JmsMessageListener {

    private final Logger LOGGER = LoggerFactory
            .getLogger(JmsListenerImpl.class);

    @Autowired
    PonctualQuoteService ponctualQuoteService;

//    @Override
//    @JmsListener(destination = "${spring.activemq.hourly.answer.queue}")
//    public void listenHourlyAnswer(final TextMessage jsonMessage) throws JMSException {
//        LOGGER.info("Listen hourly answer message received from queue");
//        LOGGER.info("data received: " + jsonMessage.getText());
//        if(jsonMessage != null) {
//            String messageData = jsonMessage.getText();
//            try {
//                PonctualQuoteDTO quote = QuoteMapper.mapJSONToQuote(messageData);
//                ponctualQuoteService.setNewHourly(quote);
//            }
//            catch (JsonProcessingException e) {
//                LOGGER.error("Error while parsing hourly request message");
//            }
//        }
//        else
//        {
//            LOGGER.error("Empty message received from hourly queue");
//        }
//    }

    @Override
    @JmsListener(destination = "${spring.activemq.lastadded.notif.queue}")
    public void listenLastAddedQuote(final TextMessage jsonMessage) throws JMSException {
        if(jsonMessage != null) {
            String messageData = jsonMessage.getText();
            LOGGER.info("Listen last added quote message received from queue");
            try {
                PonctualQuoteDTO quote = QuoteMapper.mapJSONToQuote(messageData);
                ponctualQuoteService.setNewLastAdded(quote);
            }
            catch (JsonProcessingException e) {
                LOGGER.error("Error while parsing last added quote message");
            }
        }
        else
        {
            System.out.println("Empty message received from last added quote queue");
        }
    }
}
