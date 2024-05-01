package ch.hearc.mbu.citationapi.service;

import ch.hearc.mbu.citationapi.dto.QuoteDTO;
import ch.hearc.mbu.citationapi.repository.model.Quote;
import org.springframework.stereotype.Service;

public interface QuoteService {

    public QuoteDTO convertToDTO(Quote citation);
    public Quote convertToEntity(QuoteDTO dto);

    public long create(QuoteDTO dto);
    public QuoteDTO get(long id);

    public QuoteDTO getRandom();
}
