/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: Author Controller REST Endpoints
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.web;

import ch.hearc.mbu.simplequote.dto.AuthorDTO;
import ch.hearc.mbu.simplequote.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable long id) {
        AuthorDTO authorDTO = authorService.get(id);
        if (authorDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorService.get(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAuthors() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDTO authorDTO) {
        URI location = WebMvcLinkBuilder.linkTo(AuthorController.class).slash(authorService.create(authorDTO)).toUri();
        return ResponseEntity.created(location).build();
    }
}