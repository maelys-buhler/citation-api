package ch.hearc.mbu.ponctualquote.tools;

import ch.hearc.mbu.ponctualquote.jms_async.JmsMessageProducer;
import ch.hearc.mbu.ponctualquote.jms_sync.SyncMessageClient;
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
    SyncMessageClient syncMessageClient;

    @Scheduled(fixedRate = 10 * 1000)
    public void sendHourlyRequest() {
        LOGGER.info("Hourly Scheduled Task executed");
        syncMessageClient.request("{\"type\":\"hourly\"}");
    }
}
