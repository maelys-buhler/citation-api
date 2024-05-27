/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: Quote Controller REST Endpoints
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.web;

import ch.hearc.mbu.simplequote.dto.QuoteDTO;
import ch.hearc.mbu.simplequote.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/random")
    public ResponseEntity<?> getRandomQuote() {
        QuoteDTO quote = quoteService.getRandom();
        if(quote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quote);
    }

    @PostMapping("/")
    public ResponseEntity<?> createQuote(@RequestBody QuoteDTO quoteDTO) {
        URI location = WebMvcLinkBuilder.linkTo(QuoteController.class).slash(quoteService.create(quoteDTO)).toUri();
        return ResponseEntity.created(location).build();
    }
}
