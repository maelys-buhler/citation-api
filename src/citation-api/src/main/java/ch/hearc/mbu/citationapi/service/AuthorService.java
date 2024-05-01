package ch.hearc.mbu.citationapi.service;

import ch.hearc.mbu.citationapi.dto.AuthorDTO;
import ch.hearc.mbu.citationapi.repository.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {

    public AuthorDTO convertToDTO(Author entity);
    public Author convertToEntity(AuthorDTO dto);

    public long create(AuthorDTO dto);
    public AuthorDTO get(long id);

    public List<AuthorDTO> getAll();


}
