/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Request Mapper Object to JSON and inverse
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.jms.mapper;

import ch.hearc.mbu.simplequote.dto.AuthorDTO;
import ch.hearc.mbu.simplequote.dto.HourlyRequestDTO;
import ch.hearc.mbu.simplequote.dto.QuoteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestMapper {

    public static HourlyRequestDTO mapJSONToObject(String request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String formattedRequest = request.replace("\\\"", "\"");
        System.out.println(formattedRequest);
        return objectMapper.readValue(formattedRequest, HourlyRequestDTO.class);
    }

    public static String mapQuoteToJSON(QuoteDTO quoteDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(quoteDTO);
    }

    public static String mapAuthorToJSON(AuthorDTO authorDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(authorDTO);
    }
}
