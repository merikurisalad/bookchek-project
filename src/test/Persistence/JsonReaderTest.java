package Persistence;

import model.Book;
import model.ToRead;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            ToRead tr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToRead() {
        try {
            ToRead tr = new ToRead("Weekend Reads");
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyToRead.json");
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyToRead.json");
            tr = reader.read();
            assertEquals("Weekend Reads", tr.getToReadName());
            assertEquals(0, tr.getToReadList().size());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    void testReaderNonEmptyToRead() {
        Book b1 = new Book("Harry", "Potter");
        Book b2 = new Book("To Kill", "Mockingbird");
        b1.addQuote("Ya wizard Harry");
        b1.addQuote("Shut up Malfoy");
        try {
            ToRead tr = new ToRead("Weekend Reads");
            tr.addBookToRead(b1);
            tr.addBookToRead(b2);
            tr.addCurrentRead(b1);
            tr.addCurrentRead(b2);
            JsonWriter writer = new JsonWriter("./data/testReaderNonEmptyToRead.json");
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderNonEmptyToRead.json");
            tr = reader.read();
            assertEquals("Weekend Reads", tr.getToReadName());
            List<Book> books = tr.getToReadList();
            assertEquals(2, books.size());
            checkBook("Harry", "Potter", books.get(0));
            checkBook("To Kill", "Mockingbird", books.get(1));
        } catch (IOException e) {
            fail("Could not read from file");

        }
    }
}
