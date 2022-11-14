package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setup() {
        book = new Book("No Longer Human", "Osamu Dazai");
    }

    @Test
    public void testConstructor() {
        assertEquals("No Longer Human", book.getTitle());
        assertEquals("Osamu Dazai", book.getAuthor());
        assertTrue(book.getQuotes().isEmpty());
    }

    @Test
    public void testAddQuote() {
        String quote1 = "Mine has been a life of much shame.";
        String quote2 = "Now I have neither happiness nor unhappiness. Everything passes.";
        book.addQuote(quote1);
        assertEquals(Arrays.asList(quote1), book.getQuotes());
        book.addQuote(quote2);
        assertEquals(Arrays.asList(quote1, quote2), book.getQuotes());

    }
}