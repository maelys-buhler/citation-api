package ch.hearc.mbu.ponctualquote.service;

import ch.hearc.mbu.ponctualquote.dto.AuthorDTO;
import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.repository.model.Author;
import ch.hearc.mbu.ponctualquote.repository.model.PonctualQuote;
import ch.hearc.mbu.ponctualquote.repository.model.QuoteStatus;

public interface PonctualQuoteService {
    PonctualQuote setNewLastAdded(PonctualQuoteDTO dto);
    Author setNewLastAddedAuthor(AuthorDTO dto);
    PonctualQuote setNewHourly(PonctualQuoteDTO dto);
    void setNewPlaylist();
    PonctualQuoteDTO getPlaylistQuote();
    PonctualQuoteDTO getHourlyQuote();
    PonctualQuoteDTO getLastAddedQuote();
    AuthorDTO getLastAddedAuthor();

}
