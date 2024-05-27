/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: Author Service
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.service;


import ch.hearc.mbu.simplequote.dto.AuthorDTO;
import ch.hearc.mbu.simplequote.repository.model.Author;

import java.util.List;

public interface AuthorService {

    public AuthorDTO convertToDTO(Author entity);
    public Author convertToEntity(AuthorDTO dto);

    public long create(AuthorDTO dto);
    public AuthorDTO get(long id);

    public List<AuthorDTO> getAll();


}
