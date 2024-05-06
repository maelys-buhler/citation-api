package ch.hearc.mbu.ponctualquote.jms.mapper;

import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.jms.impl.JmsProducerImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuoteMapper {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuoteMapper.class);

    public static String mapQuoteToJSON(PonctualQuoteDTO quote) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(quote);
    }

    public static PonctualQuoteDTO mapJSONToQuote(String quote) throws JsonProcessingException {
        String formattedQuote = quote.replace("\\\"", "\"");
        formattedQuote = formattedQuote.substring(1, formattedQuote.length() - 1);
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("formattedQuote: " + formattedQuote);
        return objectMapper.readValue(formattedQuote, PonctualQuoteDTO.class);
    }
}
