package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a to-read list of books
public class ToRead implements Writable {

    private String toReadName;      // to-read object name
    private List<Book> toReadList;  // books to-read
    private List<Book> currentRead; // books currently being read

    // EFFECTS: constructs a to-read object with an empty to-read list and current read list
    public ToRead(String toReadName) {
        this.toReadName = toReadName;
        toReadList = new ArrayList();
        currentRead = new ArrayList();

    }

    // MODIFIES: this
    // EFFECTS: adds a book to to-read list and logs the event
    public void addBookToRead(Book book) {
        toReadList.add(book);

        // logs add book event
        if (toReadName == "Pleasure Reads") {
            EventLog.getInstance().logEvent(
                    new Event(book.getTitle() + " was added to Pleasure Reads."));
        } else if (toReadName == "Homework Reads") {
            EventLog.getInstance().logEvent(
                    new Event(book.getTitle() + " was added to Homework Reads."));
        }

    }

    // MODIFIES: this
    // EFFECTS: sets a book as current read and logs the event
    public void addCurrentRead(Book book) {
        currentRead.add(book);

        if (toReadName == "Pleasure Reads") {
            EventLog.getInstance().logEvent(
                    new Event(book.getTitle() + " was added to current Pleasure Reads."));
        } else if (toReadName == "Homework Reads") {
            EventLog.getInstance().logEvent(
                    new Event(book.getTitle() + " was added to current Homework Reads."));
        }

    }

    // EFFECTS: get to-read list (titles)
    public List<String> getToReadListTitles() {
        List<String> listTitles = new ArrayList();
        for (Book b: toReadList) {
            listTitles.add(b.getTitle());
        }
        return listTitles;

    }

    // EFFECTS: get current read (titles)
    public List<String> getCurrentReadTitles() {
        List<String> listTitles = new ArrayList();
        for (Book b: currentRead) {
            listTitles.add(b.getTitle());
        }
        return listTitles;

    }

    // EFFECTS: get to-read object name
    public String getToReadName() {
        return toReadName;
    }

    // EFFECTS: get to-read list (books)
    public List<Book> getToReadList() {
        return toReadList;
    }

    // EFFECTS: get current read (books)
    public List<Book> getCurrentRead() {
        return currentRead;
    }

    // EFFECTS: returns to-read as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", toReadName);
        json.put("books", booksToJson());
        json.put("current", currentReadToJson());
        return json;
    }

    // EFFECTS: returns books in this to-read list to-as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : toReadList) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns books in this to-read list as a JSON array
    private JSONArray currentReadToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : currentRead) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }



}
