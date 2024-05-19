package ch.hearc.mbu.ponctualquote.jms_sync;

import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.jms_async.impl.JmsListenerImpl;
import ch.hearc.mbu.ponctualquote.jms_async.mapper.QuoteMapper;
import ch.hearc.mbu.ponctualquote.service.PonctualQuoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Function;

@Component
public class SyncMessageClient {

    // Source: https://bdarfler.medium.com/synchronous-request-response-with-activemq-and-spring-21359a438a86
    private static final class ProducerConsumer implements SessionCallback<Message> {
        private final Logger LOGGER = LoggerFactory
                .getLogger(ProducerConsumer.class);

        private static final int TIMEOUT = 5000;
        private final String msg;
        private final DestinationResolver destinationResolver;
        private final String requestQueue;
        private final String answerQueue;
        private final Function callback;
        public ProducerConsumer( final String msg, final String requestQueue, final String answerQueue, final DestinationResolver destinationResolver, final Function callback) {
            this.msg = msg;
            this.destinationResolver = destinationResolver;
            this.requestQueue = requestQueue;
            this.answerQueue = answerQueue;
            this.callback = callback;
        }
        public Message doInJms( final Session session ) throws JMSException {
            MessageConsumer consumer = null;
            MessageProducer producer = null;
            try {
                final String correlationId = UUID.randomUUID().toString();
                final Destination requestQueue =
                        destinationResolver.resolveDestinationName( session, this.requestQueue, false );
                final Destination replyQueue =
                        destinationResolver.resolveDestinationName( session, this.answerQueue, false );
                // Create the consumer first!
                consumer = session.createConsumer( replyQueue, "JMSCorrelationID = '" + correlationId + "'");
                final TextMessage textMessage = session.createTextMessage( msg );
                textMessage.setJMSCorrelationID( correlationId );
                textMessage.setJMSReplyTo( replyQueue );
                // Send the request second!
                producer = session.createProducer( requestQueue );
                producer.send( requestQueue, textMessage );
                // Block on receiving the response with a timeout
                Message message = consumer.receive(TIMEOUT);
                callback.apply(message);
                return message;
            }
            finally {
                // Don't forget to close your resources
                JmsUtils.closeMessageConsumer( consumer );
                JmsUtils.closeMessageProducer( producer );
            }
        }
    }
    private final JmsTemplate jmsTemplate;


    @Autowired
    public SyncMessageClient( final JmsTemplate jmsTemplate ) {
        this.jmsTemplate = jmsTemplate;
    }
    public String request(final String request, final String hourlyRequestQueue, final String hourlyAnswerQueue, final Function<Message, Boolean> callback) {
        // Must pass true as the second param to start the connection
        return jmsTemplate.execute( new ProducerConsumer(request, hourlyRequestQueue, hourlyAnswerQueue, jmsTemplate.getDestinationResolver(), callback), true ).toString();
    }
}
