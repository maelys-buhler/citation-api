package ch.hearc.mbu.ponctualquote.web;

import ch.hearc.mbu.ponctualquote.dto.PonctualQuoteDTO;
import ch.hearc.mbu.ponctualquote.jms_sync.CallbackCreator;
import ch.hearc.mbu.ponctualquote.jms_sync.SyncMessageClient;
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

    @Autowired
    private SyncMessageClient syncMessageClient;

    @Autowired
    private CallbackCreator callbackCreator;

    @Value("${spring.activemq.playlist.request.queue}")
    private String playlistRequestQueue;

    @Value("${spring.activemq.playlist.answer.queue}")
    private String playlistAnswerQueue;

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
            syncMessageClient.request("{\"type\":\"hourly\"}", playlistRequestQueue , playlistAnswerQueue, callbackCreator.createPlaylistAnswerCallback());
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

    @PostMapping("/next-playlist")
    public ResponseEntity<?> nextPlaylistQuote() {
        syncMessageClient.request("{\"type\":\"hourly\"}", playlistRequestQueue , playlistAnswerQueue, callbackCreator.createPlaylistAnswerCallback());
        return ResponseEntity.ok().build();
    }
}
