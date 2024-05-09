package ch.hearc.mbu.ponctualquote.repository;

import ch.hearc.mbu.ponctualquote.repository.model.PonctualQuote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PonctualQuoteRepository extends CrudRepository<PonctualQuote, Long> {
    @Query(value = "SELECT * FROM quotes WHERE status LIKE 'NONE' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    PonctualQuote getRandomNone();

    @Query(value = "SELECT * FROM quotes WHERE status LIKE 'PLAYLIST' LIMIT 1", nativeQuery = true)
    PonctualQuote getPlaylistQuote();

    @Query(value = "SELECT * FROM quotes WHERE status LIKE 'HOURLY' LIMIT 1", nativeQuery = true)
    PonctualQuote getHourlyQuote();

    @Query(value = "SELECT * FROM quotes WHERE status LIKE 'LAST_ADDED' LIMIT 1", nativeQuery = true)
    PonctualQuote getLastAddedQuote();
}
