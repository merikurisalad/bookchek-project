package persistence;

import model.Book;
import model.ToRead;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


// Represents a reader that reads to-read list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads to-read list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToRead read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToRead(jsonObject);
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses to-read list from JSON object and returns it
    private ToRead parseToRead(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ToRead tr = new ToRead(name);
        addBooks(tr, jsonObject, "books");
        addBooks(tr, jsonObject, "current");
        return tr;

    }

    // MODIFIES: tr
    // EFFECTS: parses book objects from JSON object and adds them to to-read list
    private void addBooks(ToRead tr, JSONObject jsonObject, String key) {
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(tr, nextBook, key);
        }
    }

    // MODIFIES: tr
    // EFFECTS: parses book from JSON object and adds it to to-read list
    private void addBook(ToRead tr, JSONObject jsonObject, String key) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        JSONArray quotes = jsonObject.getJSONArray("quotes");
        Book book = new Book(title, author);
        book.addMultipleQuotes(jsonArrayToList(quotes));
        if (key.equals("books")) {
            tr.addBookToRead(book);
        } else {
            tr.addCurrentRead(book);
        }


    }

    // EFFECTS: parses JSONArray and adds them to list of quotes
    private List<String> jsonArrayToList(JSONArray jsonArray) {
        List<String> quotes = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            quotes.add(jsonArray.getString(i));
        }
        return quotes;

    }
}




