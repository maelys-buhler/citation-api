/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Author Repository
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.repository;

import ch.hearc.mbu.ponctualquote.repository.model.Author;
import ch.hearc.mbu.ponctualquote.repository.model.PonctualQuote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query(value = "SELECT * FROM authors LIMIT 1", nativeQuery = true)
    Author getOne();
}
