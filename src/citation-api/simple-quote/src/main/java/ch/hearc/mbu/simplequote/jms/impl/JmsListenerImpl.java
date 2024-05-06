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
    @JmsListener(destination = "${spring.activemq.hourly.answer.queue}")
    public void listenHourlyRequest(final TextMessage jsonMessage) throws JMSException {
        String messageData = null;
        if(jsonMessage != null) {
            messageData = jsonMessage.getText();
            LOGGER.info("Listen hourly request message received from queue:");
            try {
                HourlyRequestDTO request = RequestMapper.mapJSONToObject(messageData);
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
}
