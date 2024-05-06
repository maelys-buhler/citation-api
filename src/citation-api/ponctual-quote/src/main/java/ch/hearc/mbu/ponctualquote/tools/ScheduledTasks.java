package ch.hearc.mbu.ponctualquote.tools;

import ch.hearc.mbu.ponctualquote.jms.JmsMessageProducer;
import ch.hearc.mbu.ponctualquote.jms.impl.JmsListenerImpl;
import ch.hearc.mbu.ponctualquote.service.PonctualQuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final Logger LOGGER = LoggerFactory
            .getLogger(ScheduledTasks.class);

    @Autowired
    JmsMessageProducer jmsMessageProducer;

    @Scheduled(fixedRate = 5000)
    public void sendHourlyRequest() {
        jmsMessageProducer.sendHourlyRequest();
        LOGGER.info("Hourly Scheduled Task executed");
    }
}
