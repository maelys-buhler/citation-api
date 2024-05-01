package ch.hearc.mbu.citationapi.service.impl;

import ch.hearc.mbu.citationapi.dto.QuoteDTO;
import ch.hearc.mbu.citationapi.repository.AuthorRepository;
import ch.hearc.mbu.citationapi.repository.QuoteRepository;
import ch.hearc.mbu.citationapi.repository.model.Author;
import ch.hearc.mbu.citationapi.repository.model.Quote;
import ch.hearc.mbu.citationapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public QuoteDTO convertToDTO(Quote quote) {
        QuoteDTO quoteDTO = new QuoteDTO();
        quoteDTO.setQuote(quote.getQuote());
        quoteDTO.setAuthor(quote.getAuthor().getName());
        return quoteDTO;
    }

    @Override
    public Quote convertToEntity(QuoteDTO dto) {
        Quote quote = new Quote();
        quote.setQuote(dto.getQuote());
        Author author = authorRepository.findByName(dto.getAuthor());
        if (author == null) {
            author = new Author();
            author.setName(dto.getAuthor());
        }
        quote.setAuthor(author);
        return quote;
    }

    @Override
    public long create(QuoteDTO dto) {
        authorRepository.save(convertToEntity(dto).getAuthor());
        return quoteRepository.save(convertToEntity(dto)).getId();
    }

    @Override
    public QuoteDTO get(long id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote == null) {
            return null;
        }
        return convertToDTO(quote);
    }

    @Override
    public QuoteDTO getRandom() {
        Quote quote = quoteRepository.getRandom();
        if (quote == null) {
            return null;
        }
        return convertToDTO(quote);
    }
}
