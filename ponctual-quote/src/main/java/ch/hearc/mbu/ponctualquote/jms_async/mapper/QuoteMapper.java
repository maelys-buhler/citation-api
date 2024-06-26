/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Mapper from JSON to DTO object and inverse
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.jms_async.mapper;

import ch.hearc.mbu.ponctualquote.dto.AuthorDTO;
import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
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
        LOGGER.info("Formatted quote: " + formattedQuote);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(formattedQuote, PonctualQuoteDTO.class);
    }

    public static String mapAuthorToJSON(AuthorDTO author) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(author);
    }

    public static AuthorDTO mapJSONToAuthor(String author) throws JsonProcessingException {
        String formattedAuthor = author.replace("\\\"", "\"");
        formattedAuthor = formattedAuthor.substring(1, formattedAuthor.length() - 1);
        LOGGER.info("Formatted author: " + formattedAuthor);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(formattedAuthor, AuthorDTO.class);
    }
}
