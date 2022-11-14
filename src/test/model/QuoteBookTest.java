package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuoteBookTest {

    private Book bookN;
    private Book bookJ;
    private QuoteBook qbook;
    private List<String> allQuotes;

    @BeforeEach
    public void setup() {
        bookN = new Book("No Longer Human", "Osamu Dazai");
        bookJ = new Book ("Jane Eyre", "Charlotte Bronte");
        qbook = new QuoteBook("Inspirational Quotes");
        bookN.addQuote("Mine has been a life of much shame. " +
                "I can't even guess myself what it must be to live the life of a human being.");
        bookN.addQuote("Now I have neither happiness nor unhappiness. Everything passes.");
        bookJ.addQuote("I would always rather be happy than dignified.");
    }

    @Test
    public void testConstructor() {
        assertEquals("Inspirational Quotes", qbook.getQbookName());
        assertTrue(qbook.getAllQuotes().isEmpty());

    }

    @Test
    public void testAddQuoteToQbook() {
        String quote1 = "Mine has been a life of much shame. " +
                "I can't even guess myself what it must be to live the life of a human being.";
        String quote2 = "Now I have neither happiness nor unhappiness. Everything passes.";
        String quote3 = "I would always rather be happy than dignified.";

        qbook.addQuoteToQbook(bookN);
        assertEquals(Arrays.asList(quote1, quote2), qbook.getAllQuotes());
        qbook.addQuoteToQbook(bookJ);
        assertEquals(Arrays.asList(quote1, quote2, quote3), qbook.getAllQuotes());

    }

    @Test
    public void testGetRandomQuote() {
        qbook.addQuoteToQbook(bookN);
        qbook.addQuoteToQbook(bookJ);
        // figure out random test
        assertTrue(qbook.getAllQuotes().contains(qbook.getRandomQuote()));

    }
}
