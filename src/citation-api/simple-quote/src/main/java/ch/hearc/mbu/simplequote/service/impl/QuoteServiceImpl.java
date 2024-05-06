package ch.hearc.mbu.simplequote.service.impl;

import ch.hearc.mbu.simplequote.dto.HourlyRequestDTO;
import ch.hearc.mbu.simplequote.dto.QuoteDTO;
import ch.hearc.mbu.simplequote.repository.model.Author;
import ch.hearc.mbu.simplequote.repository.model.Quote;
import ch.hearc.mbu.simplequote.repository.AuthorRepository;
import ch.hearc.mbu.simplequote.repository.QuoteRepository;
import ch.hearc.mbu.simplequote.jms.JmsMessageProducer;
import ch.hearc.mbu.simplequote.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private JmsMessageProducer jmsMessageProducer;

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
        Quote quote = quoteRepository.save(convertToEntity(dto));
        jmsMessageProducer.sendLastAddedQuote(convertToDTO(quote));
        return quote.getId();
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
    public void sendNewHourlyQuote(HourlyRequestDTO hourlyRequestDTO) {
        if (hourlyRequestDTO.getType().equals("hourly")) {
            QuoteDTO quoteDTO = getRandom();
            jmsMessageProducer.sendHourlyAnswer(quoteDTO);
        }
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
