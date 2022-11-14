package Persistence;

import java.util.List;
import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String title, String author, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());

    }
}
