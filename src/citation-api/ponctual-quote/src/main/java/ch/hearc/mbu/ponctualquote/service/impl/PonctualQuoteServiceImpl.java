package ch.hearc.mbu.ponctualquote.service.impl;

import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.repository.PonctualQuoteRepository;
import ch.hearc.mbu.ponctualquote.repository.model.PonctualQuote;
import ch.hearc.mbu.ponctualquote.repository.model.QuoteStatus;
import ch.hearc.mbu.ponctualquote.service.PonctualQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PonctualQuoteServiceImpl implements PonctualQuoteService {

    @Autowired
    private PonctualQuoteRepository ponctualQuoteRepository;

    private PonctualQuoteDTO convertToDTO(PonctualQuote entity) {
        PonctualQuoteDTO dto = new PonctualQuoteDTO();
        dto.setQuote(entity.getQuote());
        dto.setAuthor(entity.getAuthor());
        return dto;
    }

    @Override
    public PonctualQuote setNewLastAdded(PonctualQuoteDTO dto) {
        PonctualQuote entity = new PonctualQuote();
        entity.setQuote(dto.getQuote());
        entity.setAuthor(dto.getAuthor());
        entity.setStatus(QuoteStatus.LAST_ADDED);
        ponctualQuoteRepository.save(entity);
        return entity;
    }

    @Override
    public PonctualQuote setNewHourly(PonctualQuoteDTO dto) {
        PonctualQuote entity = new PonctualQuote();
        entity.setQuote(dto.getQuote());
        entity.setAuthor(dto.getAuthor());
        entity.setStatus(QuoteStatus.HOURLY);
        ponctualQuoteRepository.save(entity);
        return entity;
    }

    @Override
    public void nextPlaylistQuote() {
        PonctualQuote entity = ponctualQuoteRepository.getPlaylistQuote();
        entity.setStatus(QuoteStatus.NONE);
        ponctualQuoteRepository.save(entity);
        PonctualQuote newPlaylistQuote = ponctualQuoteRepository.getRandomNone();
        newPlaylistQuote.setStatus(QuoteStatus.PLAYLIST);
        ponctualQuoteRepository.save(newPlaylistQuote);
    }

    @Override
    public PonctualQuoteDTO getPlaylistQuote() {
        PonctualQuote entity = ponctualQuoteRepository.getPlaylistQuote();
        return convertToDTO(entity);
    }

    @Override
    public PonctualQuoteDTO getHourlyQuote() {
        PonctualQuote entity = ponctualQuoteRepository.getHourlyQuote();
        return convertToDTO(entity);
    }

    @Override
    public PonctualQuoteDTO getLastAddedQuote() {
        PonctualQuote entity = ponctualQuoteRepository.getLastAddedQuote();
        return convertToDTO(entity);
    }
}
