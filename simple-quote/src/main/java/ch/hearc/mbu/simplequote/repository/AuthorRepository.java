/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: Author Repository
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.repository;

import ch.hearc.mbu.simplequote.repository.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    public Author findByName(String name);
    public List<Author> findAll();
}
