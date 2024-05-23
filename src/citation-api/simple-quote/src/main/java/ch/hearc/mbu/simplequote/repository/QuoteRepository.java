/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: Quote Repository
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.repository;

import ch.hearc.mbu.simplequote.repository.model.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long> {
    @Query(value = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote getRandom();
}
