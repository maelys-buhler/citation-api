/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: Author Service Implementation
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.service.impl;

import ch.hearc.mbu.simplequote.dto.AuthorDTO;
import ch.hearc.mbu.simplequote.repository.AuthorRepository;
import ch.hearc.mbu.simplequote.repository.model.Author;
import ch.hearc.mbu.simplequote.service.AuthorService;
import ch.hearc.mbu.simplequote.jms.JmsMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private JmsMessageProducer jmsMessageProducer;

    @Override
    public AuthorDTO convertToDTO(Author entity) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(entity.getName());
        return authorDTO;
    }

    @Override
    public Author convertToEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        return author;
    }

    @Override
    public long create(AuthorDTO dto) {
        Author author =  authorRepository.save(convertToEntity(dto));
        jmsMessageProducer.sendLastAddedAuthor(convertToDTO(author));
        return author.getId();
    }

    @Override
    public AuthorDTO get(long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return null;
        }
        return convertToDTO(author);
    }

    @Override
    public List<AuthorDTO> getAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(this::convertToDTO).toList();
    }
}
