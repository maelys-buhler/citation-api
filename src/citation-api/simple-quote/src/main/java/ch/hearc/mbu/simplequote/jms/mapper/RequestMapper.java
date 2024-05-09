package ch.hearc.mbu.simplequote.jms.mapper;

import ch.hearc.mbu.simplequote.dto.HourlyRequestDTO;
import ch.hearc.mbu.simplequote.dto.QuoteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestMapper {

    public static HourlyRequestDTO mapJSONToObject(String request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String formattedRequest = request.replace("\\\"", "\"");
        formattedRequest = formattedRequest.substring(1, formattedRequest.length() - 1);
        return objectMapper.readValue(formattedRequest, HourlyRequestDTO.class);
    }

    public static String mapQuoteToJSON(QuoteDTO quoteDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(quoteDTO);
    }
}