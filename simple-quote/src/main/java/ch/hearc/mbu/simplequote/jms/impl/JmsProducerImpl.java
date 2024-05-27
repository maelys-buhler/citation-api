/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Implementation of the JMS Producer
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.jms.impl;

import ch.hearc.mbu.simplequote.dto.AuthorDTO;
import ch.hearc.mbu.simplequote.dto.QuoteDTO;
import ch.hearc.mbu.simplequote.jms.mapper.RequestMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ch.hearc.mbu.simplequote.jms.JmsMessageProducer;

@Component
public class JmsProducerImpl implements JmsMessageProducer {
    private final Logger LOGGER = LoggerFactory
            .getLogger(JmsProducerImpl.class);

    @Value("${spring.activemq.hourly.answer.queue}")
    String hourlyAnswerQueue;

    @Value("${spring.activemq.lastadded.notif.queue}")
    String lastAddedQuoteQueue;

    @Value("${spring.activemq.lastadded.notif.author.queue}")
    String lastAddedAuthorQueue;

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void sendHourlyAnswer(QuoteDTO quoteDTO, String correlationID) {
        String jsonMessage = null;
        try {

            jsonMessage = "{\"error\":\"No quote available\"}";
            if(quoteDTO != null)
            {
                LOGGER.info("Hourly request received: " + quoteDTO.toString());
                jsonMessage = RequestMapper.mapQuoteToJSON(quoteDTO);
            }
            LOGGER.info("Hourly request: " + jsonMessage);
            jmsTemplate.convertAndSend(hourlyAnswerQueue, jsonMessage, message -> {
                message.setJMSCorrelationID(correlationID);
                return message;
            });
            LOGGER.info("Hourly request sent to queue: " + hourlyAnswerQueue);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while mapping QuoteDTO to JSON: " + e.getMessage());
        }
    }

    @Override
    public void sendLastAddedQuote(QuoteDTO quoteDTO) {
        String jsonMessage = null;
        try {
            jsonMessage = RequestMapper.mapQuoteToJSON(quoteDTO);
            LOGGER.info("Last added notification: " + jsonMessage);
            jmsTemplate.convertAndSend(lastAddedQuoteQueue, jsonMessage);
            LOGGER.info("Last added notification sent to queue: " + lastAddedQuoteQueue);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while mapping QuoteDTO to JSON: " + e.getMessage());
        }
    }

    @Override
    public void sendLastAddedAuthor(AuthorDTO authorDTO) {
        String jsonMessage = null;
        try {
            jsonMessage = RequestMapper.mapAuthorToJSON(authorDTO);
            LOGGER.info("Last added author notification: " + jsonMessage);
            jmsTemplate.convertAndSend(lastAddedAuthorQueue, jsonMessage);
            LOGGER.info("Last added author notification sent to queue: " + lastAddedAuthorQueue);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while mapping QuoteDTO to JSON: " + e.getMessage());
        }
    }
}
