/*
 * Author: Maëlys Bühler
 * Service: Ponctual Quote
 * Content: Model of response body from simple-quote
 * Date: May 2024
 */

package ch.hearc.mbu.ponctualquote.remote.models;

public class QuoteResponseBody {
    private String quote;
    private String author;

    public QuoteResponseBody() {
    }

    public QuoteResponseBody(String quote, String author) {
        this.quote = quote;
        this.author = author;
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
