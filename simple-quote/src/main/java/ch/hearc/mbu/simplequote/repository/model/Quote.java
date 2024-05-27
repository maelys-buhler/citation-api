/*
 * Author: Maëlys Bühler
 * Service: Simple Quote
 * Content: Quote Model
 * Date: May 2024
 */

package ch.hearc.mbu.simplequote.repository.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String quote;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private Author author;

    public long getId()
    {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
