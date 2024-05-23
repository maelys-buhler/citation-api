/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Controller to define REST entrypoint
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.web;

import ch.hearc.mbu.ponctualquote.dto.AuthorDTO;
import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.jms_sync.ActionCreator;
import ch.hearc.mbu.ponctualquote.jms_sync.SyncMessageClient;
import ch.hearc.mbu.ponctualquote.repository.model.Author;
import ch.hearc.mbu.ponctualquote.service.PonctualQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PonctualQuoteController {

    @Autowired
    private PonctualQuoteService ponctualQuoteService;

    @GetMapping("/hourly")
    public ResponseEntity<?> getHourlyQuote() {
         PonctualQuoteDTO quote = ponctualQuoteService.getHourlyQuote();
         if(quote == null) {
             return ResponseEntity.notFound().build();
         }
        return ResponseEntity.ok(quote);
    }

    @GetMapping("/playlist")
    public ResponseEntity<?> getPlaylistQuote() {
        PonctualQuoteDTO quote = ponctualQuoteService.getPlaylistQuote();
        if(quote == null) {
            ponctualQuoteService.setNewPlaylist();
            quote = ponctualQuoteService.getPlaylistQuote();
            if(quote == null)
            {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(quote);
    }

    @GetMapping("/last-added")
    public ResponseEntity<?> getLastAddedQuote() {
        PonctualQuoteDTO quote = ponctualQuoteService.getLastAddedQuote();
        if(quote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quote);
    }

    @GetMapping("/last-added-author")
    public ResponseEntity<?> getLastAddedAuthor() {
        AuthorDTO author = ponctualQuoteService.getLastAddedAuthor();
        if(author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping("/next-playlist")
    public ResponseEntity<?> nextPlaylistQuote() {
        ponctualQuoteService.setNewPlaylist();
        return ResponseEntity.ok().build();
    }
}
