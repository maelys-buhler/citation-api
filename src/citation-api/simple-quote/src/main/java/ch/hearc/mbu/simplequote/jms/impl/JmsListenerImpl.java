package ch.hearc.mbu.simplequote.jms.impl;

import ch.hearc.mbu.simplequote.dto.HourlyRequestDTO;
import ch.hearc.mbu.simplequote.jms.JmsMessageListener;
import ch.hearc.mbu.simplequote.jms.mapper.RequestMapper;
import ch.hearc.mbu.simplequote.service.QuoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsListenerImpl implements JmsMessageListener {

    private final Logger LOGGER = LoggerFactory
            .getLogger(JmsListenerImpl.class);

    @Autowired
    QuoteService quoteService;

    @Override
    @JmsListener(destination = "${spring.activemq.hourly.request.queue}")
    public void listenHourlyRequest(final TextMessage jsonMessage) throws JMSException {
        String messageData = null;
        if(jsonMessage != null) {
            messageData = jsonMessage.getText();
            String correlationID = jsonMessage.getJMSCorrelationID();
            LOGGER.info("Listen hourly request message received from queue");
            try {
                HourlyRequestDTO request = RequestMapper.mapJSONToObject(messageData);
                quoteService.sendNewHourlyQuote(request, correlationID);
            }
            catch (JsonProcessingException e) {
                LOGGER.error("Error while parsing hourly request message");
            }
        }
        else
        {
            LOGGER.error("Empty message received from hourly queue");
        }
    }

    @JmsListener(destination = "${spring.activemq.playlist.request.queue}")
    public void listenPlaylistRequest(final TextMessage jsonMessage) throws JMSException {
        String messageData = null;
        if(jsonMessage != null) {
            messageData = jsonMessage.getText();
            String correlationID = jsonMessage.getJMSCorrelationID();
            LOGGER.info("Listen playlist request message received from queue");
            try {
                HourlyRequestDTO request = RequestMapper.mapJSONToObject(messageData);
                quoteService.sendNewPlaylistQuote(request, correlationID);
            }
            catch (JsonProcessingException e) {
                LOGGER.error("Error while parsing playlist request message");
            }
        }
        else
        {
            LOGGER.error("Empty message received from hourly queue");
        }
    }
}
