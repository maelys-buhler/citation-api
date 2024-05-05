package ch.hearc.mbu.ponctualquote.repository.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "poncual_quotes")
public class PonctualQuote {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String quote;

    private String author;

    @Enumerated(EnumType.STRING)
    private QuoteStatus status;

    public QuoteStatus getStatus() {
        return status;
    }

    public void setStatus(QuoteStatus status) {
        this.status = status;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
