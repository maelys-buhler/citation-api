package ch.hearc.mbu.citationapi.repository;

import ch.hearc.mbu.citationapi.repository.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    public Author findByName(String name);
    public List<Author> findAll();
}
