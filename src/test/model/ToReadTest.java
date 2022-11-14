package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToReadTest {

    private ToRead toRead;
    private List<Book> toReadList;
    private List<Book> currentRead;
    private Book bookN;
    private Book bookJ;

    @BeforeEach
    public void setup() {
        toRead = new ToRead("Pleasure Reads");
        toReadList = new ArrayList();
        currentRead = new ArrayList();
        bookN = new Book("No Longer Human", "Osamu Dazai");
        bookJ = new Book("Jane Eyre", "Charlotte Bronte");
    }

    @Test
    public void testConstructor() {
        assertEquals("Pleasure Reads", toRead.getToReadName());
        assertTrue(toReadList.isEmpty());
        assertTrue(currentRead.isEmpty());

    }

    @Test
    public void testAddBookToRead() {
        toRead.addBookToRead(bookN);
        assertEquals(Arrays.asList(bookN), toRead.getToReadList());
        toRead.addBookToRead(bookJ);
        assertEquals(Arrays.asList(bookN, bookJ), toRead.getToReadList());
    }

    @Test
    public void testSetCurrentRead() {
        toRead.addCurrentRead(bookN);
        assertEquals(Arrays.asList(bookN), toRead.getCurrentRead());
        toRead.addCurrentRead(bookJ);
        assertEquals(Arrays.asList(bookN, bookJ), toRead.getCurrentRead());


    }

    @Test
    public void testGetToReadListTitles() {
        toRead.addBookToRead(bookN);
        assertEquals(Arrays.asList(bookN.getTitle()), toRead.getToReadListTitles());
        toRead.addBookToRead(bookJ);
        assertEquals(Arrays.asList(bookN.getTitle(), bookJ.getTitle()), toRead.getToReadListTitles());
    }

    @Test
    public void testGetCurrentReadTitles() {
        toRead.addCurrentRead(bookN);
        assertEquals(Arrays.asList(bookN.getTitle()), toRead.getCurrentReadTitles());
        toRead.addCurrentRead(bookJ);
        assertEquals(Arrays.asList(bookN.getTitle(), bookJ.getTitle()), toRead.getCurrentReadTitles());
    }
}
