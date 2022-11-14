package ui.console;

import model.Book;
import model.QuoteBook;
import model.ToRead;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Reading log app
public class ReadingLogApp {
    private static final String JSON_STORE1 = "./data/pleasureReads.json";
    private static final String JSON_STORE2 = "./data/homeworkReads.json";
    private Book bookN;
    private Book bookJ;
    private ToRead pleasureReads;
    private ToRead homeworkReads;
    private QuoteBook qbook;
    private Scanner input;
    private JsonWriter jsonWriter1;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader1;
    private JsonReader jsonReader2;

    // EFFECTS: runs the reading log application
    public ReadingLogApp() throws FileNotFoundException {
        runReadingLog();

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runReadingLog() {
        boolean keepGoing = true;
        String command = "";

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Happy reading!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("p")) {
            printToReadList();
        } else if (command.equals("b")) {
            addBookToRead();
        } else if (command.equals("c")) {
            addCurrentRead();
        } else if (command.equals("q")) {
            addQuote();
        } else if (command.equals("d")) {
            getRandomQuote();
        } else if (command.equals("s")) {
            selectToReadToSave();
        } else if (command.equals("l")) {
            selectToReadToLoad();
        } else {
            System.out.println("Please select again:");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes objects for reading log application
    private void init() {
        // initializes book objects
        bookN = new Book("No Longer Human", "Osamu Dazai");
        bookN.addQuote("Mine has been a life of much shame.");
        bookN.addQuote("Now I have neither happiness nor unhappiness. Everything passes.");
        bookJ = new Book("Jane Eyre", "Charlotte Bronte");
        bookJ.addQuote("I would always rather be happy than dignified.");

        // initializes to read objects
        pleasureReads = new ToRead("Pleasure Reads");
        homeworkReads = new ToRead("Homework Reads");
        pleasureReads.addBookToRead(bookN);
        homeworkReads.addBookToRead(bookJ);

        // initializes quotebook object
        qbook = new QuoteBook("Inspirational Quotes");
        qbook.addQuoteToQbook(bookN);
        qbook.addQuoteToQbook(bookJ);

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        // initializes persistence objects
        // saving & loading for pleasure reads
        jsonWriter1 = new JsonWriter(JSON_STORE1);
        jsonReader1 = new JsonReader(JSON_STORE1);
        // saving & loading for homework reads
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp -> Print to-read list");
        System.out.println("\tb -> Add new book to to-read list");
        System.out.println("\tc -> Set my current read");
        System.out.println("\tq -> Add quote to book");
        System.out.println("\td -> Get quote of the day");
        System.out.println("\ts -> Save to-read list to file");
        System.out.println("\tl -> Load to-read list from file");
        System.out.println("\te -> Exit");
    }

    // EFFECTS: prints every book object in selected to-read list
    private void printToReadList() {
        ToRead tr = selectToRead();
        System.out.println("Your current read(s): " + tr.getCurrentReadTitles());
        for (Book b : tr.getToReadList()) {
            System.out.println(b.getTitle() + " by " + b.getAuthor());
            System.out.println("Quotes from " + b.getTitle() + ":");
            System.out.println(b.getQuotes());

        }
    }


    // REQUIRES: input must be non-empty string
    // MODIFIES: this (ToRead)
    // EFFECTS: creates and adds a book to selected To Read category
    private void addBookToRead() {
        ToRead selected = selectToRead();
        String tempTitle = "";
        System.out.println("Input title of book:");
        tempTitle = input.next();
        while (tempTitle.equals("")) {
            System.out.println("Title cannot be blank.");
            tempTitle = input.next();
        }
        String tempAuthor = "";
        System.out.println("Enter author of book:");
        tempAuthor = input.next();
        while (tempAuthor.equals("")) {
            System.out.println("Author name cannot be blank.");
            tempAuthor = input.next();
        }
        Book newBook = new Book(tempTitle, tempAuthor);
        selected.addBookToRead(newBook);
        System.out.println(newBook.getTitle() + " by " + newBook.getAuthor()
                + " has been added to " + selected.getToReadName() + ".");
    }

    // MODIFIES: this (ToRead)
    // EFFECTS: adds book from to-read list to current read list
    private void addCurrentRead() {
        ToRead selected = selectToRead();
        if (selected.getCurrentRead().isEmpty()) {
            System.out.println("You have no current reads! Add from your to-read list for "
                    + selected.getToReadName() + ".");
            System.out.println(selected.getToReadListTitles());
            selected.addCurrentRead(selectBook(selected));
            System.out.println("Your current read has been updated to: ");
            System.out.println(selected.getCurrentReadTitles());

        } else {
            System.out.println("Here is your current reads for " + selected.getToReadName() + ":");
            System.out.println(selected.getCurrentReadTitles());
            System.out.println("Add from your to-read list for " + selected.getToReadName() + ":");
            System.out.println(selected.getToReadListTitles());
            selected.addCurrentRead(selectBook(selected));
            System.out.println("Your current read has been updated to: ");
            System.out.println(selected.getCurrentReadTitles());

        }


    }

    // REQUIRES: input must be non-empty string
    // MODIFIES: this (Book, Qbook)
    // EFFECTS: adds quote to selected book and quote book
    private void addQuote() {
        ToRead selectedToRead = selectToRead();
        System.out.println("Here is your to-read list for " + selectedToRead.getToReadName() + ".");
        System.out.println(selectedToRead.getToReadListTitles());
        Book selectedBook = selectBook(selectedToRead);
        System.out.println("Enter quote you wish to add:");
        String tempQuote = input.next();
        while (tempQuote.equals("")) {
            System.out.println("Quote cannot be blank.");
            tempQuote = input.next();
        }
        selectedBook.addQuote(tempQuote);
        qbook.addQuoteToQbook(selectedBook);
        System.out.println("Your quote from " + selectedBook.getTitle()
                + " has been added. It was also added to " + qbook.getQbookName());
    }


    // EFFECTS: get random quote from quote book
    private void getRandomQuote() {
        if (qbook.getAllQuotes().isEmpty()) {
            System.out.println("You have no quotes recorded yet.");
        } else {
            System.out.println(qbook.getRandomQuote());

        }
    }

    // REQUIRES: input must be non-empty string
    // EFFECTS: prompts user to select pleasure reads or homework reads and returns it
    private ToRead selectToRead() {
        String selection = "";  // force entry into loop
        while (!(selection.equalsIgnoreCase("p") || selection.equalsIgnoreCase("h"))) {
            System.out.println("Select to-read category:");
            System.out.println("p - Pleasure Reads");
            System.out.println("h - Homework Reads");
            selection = input.next();
        }

        if (selection.equals("p")) {
            return pleasureReads;
        } else {
            return homeworkReads;
        }
    }

    // REQUIRES: input must be non-empty string
    // EFFECTS: prompts user to input title of book to get book
    private Book selectBook(ToRead toRead) {
        String selection = "";  // force entry into loop
        while (!(toRead.getToReadListTitles().contains(selection))) {
            System.out.println("Input title of book:");
            selection = input.next();
        }
        Book tempBook = new Book("default", "default");
        for (Book b: toRead.getToReadList()) {
            if (selection.equals(b.getTitle())) {
                tempBook = b;
            }
        }
        return tempBook;
    }

    // EFFECTS: prompts user to choose which to read category they wish to save
    private void selectToReadToSave() {
        String selection = "";  // force entry into loop
        while (!(selection.equalsIgnoreCase("p") || selection.equalsIgnoreCase("h"))) {
            System.out.println("Which to read list which you like to save?");
            System.out.println("p - Pleasure Reads");
            System.out.println("h - Homework Reads");
            selection = input.next();
        }

        if (selection.equals("p")) {
            saveToRead(pleasureReads);
        } else {
            saveToRead(homeworkReads);
        }
    }

    // EFFECTS: save the to-read list to file
    private void saveToRead(ToRead toRead) {
        if (toRead.getToReadName().equals("Pleasure Reads")) {
            try {
                jsonWriter1.open();
                jsonWriter1.write(pleasureReads);
                jsonWriter1.close();
                System.out.println("Saved " + pleasureReads.getToReadName() + " to " + JSON_STORE1);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE1);
            }
        } else {
            try {
                jsonWriter2.open();
                jsonWriter2.write(homeworkReads);
                jsonWriter2.close();
                System.out.println("Saved " + homeworkReads.getToReadName() + " to " + JSON_STORE2);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE2);
            }
        }
    }

    // EFFECTS: prompts user to choose which to read category they wish to save
    private void selectToReadToLoad() {
        String selection = "";  // force entry into loop
        while (!(selection.equalsIgnoreCase("p") || selection.equalsIgnoreCase("h"))) {
            System.out.println("Which to read list which you like to load?");
            System.out.println("p - Pleasure Reads");
            System.out.println("h - Homework Reads");
            selection = input.next();
        }

        if (selection.equals("p")) {
            loadToRead(pleasureReads);
        } else {
            loadToRead(homeworkReads);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the to-read list from file
    private void loadToRead(ToRead toRead) {
        if (toRead.getToReadName().equals("Pleasure Reads")) {
            try {
                pleasureReads = jsonReader1.read();
                System.out.println("Loaded " + pleasureReads.getToReadName() + " from " + JSON_STORE1);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE1);
            }
        } else {
            try {
                homeworkReads = jsonReader2.read();
                System.out.println("Loaded " + homeworkReads.getToReadName() + " from " + JSON_STORE2);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE2);
            }
        }
    }
}