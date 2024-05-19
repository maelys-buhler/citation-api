package ch.hearc.mbu.ponctualquote.tools;
import ch.hearc.mbu.ponctualquote.jms_sync.ActionCreator;
import ch.hearc.mbu.ponctualquote.jms_sync.SyncMessageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {
    private final Logger LOGGER = LoggerFactory
            .getLogger(ScheduledTasks.class);

    @Autowired
    SyncMessageClient syncMessageClient;

    @Autowired
    ActionCreator actionCreator;

    @Value("${spring.activemq.hourly.answer.queue}")
    private String hourlyAnswerQueue;

    @Value("${spring.activemq.hourly.request.queue}")
    private String hourlyRequestQueue;

    @Scheduled(fixedRate = 10 * 1000)
    public void sendHourlyRequest() {
        LOGGER.info("Hourly Scheduled Task executed");
        syncMessageClient.request("{\"type\":\"hourly\"}", hourlyRequestQueue, hourlyAnswerQueue, actionCreator.createHourlyAnswerAction());
    }
}
