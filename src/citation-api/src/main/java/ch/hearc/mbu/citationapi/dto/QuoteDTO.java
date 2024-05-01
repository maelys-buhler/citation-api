package ch.hearc.mbu.citationapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class QuoteDTO {

    @NotBlank(message = "Quote is mandatory")
    @NotEmpty(message = "Quote cannot be empty")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String quote;

    @NotBlank(message = "Name is mandatory")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String author;

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
