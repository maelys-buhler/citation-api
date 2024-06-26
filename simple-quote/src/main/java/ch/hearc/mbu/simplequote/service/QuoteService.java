/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: Quote Service
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.service;

import ch.hearc.mbu.simplequote.dto.HourlyRequestDTO;
import ch.hearc.mbu.simplequote.dto.QuoteDTO;
import ch.hearc.mbu.simplequote.repository.model.Quote;

public interface QuoteService {

    public QuoteDTO convertToDTO(Quote citation);
    public Quote convertToEntity(QuoteDTO dto);

    public long create(QuoteDTO dto);
    public QuoteDTO get(long id);
    public void sendNewHourlyQuote(HourlyRequestDTO hourlyRequestDTO, String correlationID);

    public QuoteDTO getRandom();
}
