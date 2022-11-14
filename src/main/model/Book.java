package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a book having a title, author and list of quotes
public class Book implements Writable {
    private String title;           // title of book
    private String author;          // name of author
    private List<String> quotes;    // list of quotes from book

    // EFFECTS: constructs a book with given title, author and empty list of quotes
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.quotes = new ArrayList();

    }

    // REQUIRES: quote must be a non-empty string
    // MODIFIES: this
    // EFFECTS: adds quote to this list of quotes
    public void addQuote(String quote) {
        this.quotes.add(quote);

    }

    // REQUIRES: list of quotes must be a non-empty list
    // MODIFIES: this
    // EFFECTS: adds multiple quotes to this list of quotes
    public void addMultipleQuotes(List<String> quotes) {
        this.quotes.addAll(quotes);
    }

    // Getter methods

    // EFFECTS: returns title of book
    public String getTitle() {
        return title;

    }

    // EFFECTS: returns author of book
    public String getAuthor() {
        return author;

    }

    // EFFECTS: returns quotes from book
    public List<String> getQuotes() {
        return quotes;

    }

    // EFFECTS: returns book as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("quotes", new JSONArray(quotes));
        return json;
    }

}
