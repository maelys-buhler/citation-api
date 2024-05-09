package ch.hearc.mbu.ponctualquote.jms.impl;

import ch.hearc.mbu.ponctualquote.jms.JmsMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducerImpl implements JmsMessageProducer {
    private final Logger LOGGER = LoggerFactory
            .getLogger(JmsProducerImpl.class);

    @Value("${spring.activemq.hourly.request.queue}")
    String hourlyRequestQueue;

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void sendHourlyRequest() {
        String jsonMessage = "{\"type\":\"hourly\"}";
        jmsTemplate.convertAndSend(hourlyRequestQueue, jsonMessage);
        LOGGER.info("Hourly request sent to queue: " + hourlyRequestQueue);
    }
}
