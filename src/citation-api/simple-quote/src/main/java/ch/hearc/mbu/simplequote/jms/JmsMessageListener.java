package ch.hearc.mbu.simplequote.jms;

import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

public interface JmsMessageListener {
    void listenHourlyRequest(final TextMessage jsonMessage) throws JMSException;
}
