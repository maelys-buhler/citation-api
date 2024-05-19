package ch.hearc.mbu.ponctualquote.service.impl;

import ch.hearc.mbu.ponctualquote.dto.AuthorDTO;
import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.jms_sync.SyncMessageClient;
import ch.hearc.mbu.ponctualquote.repository.AuthorRepository;
import ch.hearc.mbu.ponctualquote.repository.PonctualQuoteRepository;
import ch.hearc.mbu.ponctualquote.repository.model.Author;
import ch.hearc.mbu.ponctualquote.repository.model.PonctualQuote;
import ch.hearc.mbu.ponctualquote.repository.model.QuoteStatus;
import ch.hearc.mbu.ponctualquote.service.PonctualQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PonctualQuoteServiceImpl implements PonctualQuoteService {

    @Autowired
    private PonctualQuoteRepository ponctualQuoteRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private SyncMessageClient syncMessageClient;

    private PonctualQuoteDTO convertToDTO(PonctualQuote entity) {
        PonctualQuoteDTO dto = new PonctualQuoteDTO();
        dto.setQuote(entity.getQuote());
        dto.setAuthor(entity.getAuthor());
        return dto;
    }

    @Override
    public PonctualQuote setNewLastAdded(PonctualQuoteDTO dto) {
        PonctualQuote entity = ponctualQuoteRepository.getLastAddedQuote();
        if (entity != null) {
            entity.setStatus(QuoteStatus.NONE);
            ponctualQuoteRepository.save(entity);
        }
        entity = new PonctualQuote();
        entity.setQuote(dto.getQuote());
        entity.setAuthor(dto.getAuthor());
        entity.setStatus(QuoteStatus.LAST_ADDED);
        ponctualQuoteRepository.save(entity);
        return entity;
    }

    @Override
    public Author setNewLastAddedAuthor(AuthorDTO dto)
    {
        Author author = authorRepository.getOne();
        if(author != null) {
            author.setName(dto.getName());
            authorRepository.save(author);
        }
        else {
            author = new Author();
            author.setName(dto.getName());
            authorRepository.save(author);
        }
        return author;
    }

    @Override
    public PonctualQuote setNewHourly(PonctualQuoteDTO dto) {
        PonctualQuote entity = ponctualQuoteRepository.getHourlyQuote();
        if (entity != null) {
            entity.setStatus(QuoteStatus.NONE);
            ponctualQuoteRepository.save(entity);
        }
        PonctualQuote newPlaylistQuote = new PonctualQuote();
        newPlaylistQuote.setQuote(dto.getQuote());
        newPlaylistQuote.setAuthor(dto.getAuthor());
        newPlaylistQuote.setStatus(QuoteStatus.HOURLY);
        ponctualQuoteRepository.save(newPlaylistQuote);
        return newPlaylistQuote;
    }

    @Override
    public PonctualQuote setNewPlaylist(PonctualQuoteDTO dto) {
        PonctualQuote entity = ponctualQuoteRepository.getPlaylistQuote();
        if (entity != null) {
            entity.setStatus(QuoteStatus.NONE);
            ponctualQuoteRepository.save(entity);
        }
        PonctualQuote newPlaylistQuote = new PonctualQuote();
        newPlaylistQuote.setQuote(dto.getQuote());
        newPlaylistQuote.setAuthor(dto.getAuthor());
        newPlaylistQuote.setStatus(QuoteStatus.PLAYLIST);
        ponctualQuoteRepository.save(newPlaylistQuote);
        return newPlaylistQuote;
    }

    @Override
    public PonctualQuoteDTO getPlaylistQuote() {
        PonctualQuote entity = ponctualQuoteRepository.getPlaylistQuote();
        if(entity == null) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    public PonctualQuoteDTO getHourlyQuote() {
        PonctualQuote entity = ponctualQuoteRepository.getHourlyQuote();
        if(entity == null) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    public PonctualQuoteDTO getLastAddedQuote() {
        PonctualQuote entity = ponctualQuoteRepository.getLastAddedQuote();
        if(entity == null) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    public AuthorDTO getLastAddedAuthor() {
        Author entity = authorRepository.getOne();
        if(entity == null) {
            return null;
        }
        AuthorDTO dto = new AuthorDTO();
        dto.setName(entity.getName());
        return dto;
    }
}
