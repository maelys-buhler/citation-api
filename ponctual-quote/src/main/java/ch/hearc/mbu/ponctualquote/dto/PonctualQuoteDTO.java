/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Quote DTO Object
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.dto;


public class PonctualQuoteDTO {
    private String quote;
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
