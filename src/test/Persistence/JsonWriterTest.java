package Persistence;

import model.Book;
import model.ToRead;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonWriterTest extends JsonTest {

    @BeforeEach
    public void setup() {
        ToRead tr = new ToRead("Weekend Reads");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            ToRead tr = new ToRead("Weekend Reads");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:filename.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToRead() {
        try {
            ToRead tr = new ToRead("Weekend Reads");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToRead.json");
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToRead.json");
            tr = reader.read();
            assertEquals("Weekend Reads", tr.getToReadName());
            assertEquals(0, tr.getToReadList().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");

        }
    }

    @Test
    void testWriterNonEmptyToRead() {
        Book b1 = new Book("Harry", "Potter");
        Book b2 = new Book("To Kill", "Mockingbird");
        try {
            ToRead tr = new ToRead("Weekend Reads");
            tr.addBookToRead(b1);
            tr.addBookToRead(b2);
            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyToRead.json");
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyToRead.json");
            tr = reader.read();
            assertEquals("Weekend Reads", tr.getToReadName());
            List<Book> books = tr.getToReadList();
            assertEquals(2, books.size());
            checkBook("Harry", "Potter", books.get(0));
            checkBook("To Kill", "Mockingbird", books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");

        }
    }
}
