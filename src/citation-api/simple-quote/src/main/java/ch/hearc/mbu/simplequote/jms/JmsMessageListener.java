/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: JMS Message Listener Interface
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.jms;

import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

public interface JmsMessageListener {
    void listenHourlyRequest(final TextMessage jsonMessage) throws JMSException;
}
