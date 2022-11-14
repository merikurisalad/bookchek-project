package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

// Represents compilation of quotes from all books recorded
public class QuoteBook {

    private String qbookName;       // quote book object name
    private List<String> allQuotes; // list of all quotes compiled so far

    // EFFECTS: constructs a quotebook with an empty list of quotes
    public QuoteBook(String qbookName) {
        this.qbookName = qbookName;
        allQuotes = new ArrayList<>();

    }

    // EFFECTS: adds quote(s) from book to qbook
    public void addQuoteToQbook(Book book) {
        allQuotes.addAll(book.getQuotes());
    }

    // EFFECTS: get randomly selected quote from qbook
    public String getRandomQuote() {
        Random r = new Random();
        return allQuotes.get(r.nextInt(allQuotes.size()));
    }

    // get qbook object name
    public String getQbookName() {
        return qbookName;
    }

    // get qbook with all quotes added so far
    public List<String> getAllQuotes() {
        return allQuotes; // stub
    }
}
