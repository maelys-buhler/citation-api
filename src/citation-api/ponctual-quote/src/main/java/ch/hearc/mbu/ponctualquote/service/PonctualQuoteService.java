package ch.hearc.mbu.ponctualquote.service;

import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.repository.model.PonctualQuote;
import ch.hearc.mbu.ponctualquote.repository.model.QuoteStatus;

public interface PonctualQuoteService {
    PonctualQuote setNewLastAdded(PonctualQuoteDTO dto);
    PonctualQuote setNewHourly(PonctualQuoteDTO dto);
    void nextPlaylistQuote();
    PonctualQuoteDTO getPlaylistQuote();
    PonctualQuoteDTO getHourlyQuote();
    PonctualQuoteDTO getLastAddedQuote();

}
