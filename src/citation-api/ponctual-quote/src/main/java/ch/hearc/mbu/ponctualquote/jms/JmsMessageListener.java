package ch.hearc.mbu.ponctualquote.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

public interface JmsMessageListener {
    void listenHourlyAnswer(final TextMessage jsonMessage) throws JMSException;
    void listenLastAddedQuote(final TextMessage jsonMessage) throws JMSException, JsonProcessingException;
}
