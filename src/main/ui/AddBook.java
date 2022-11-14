package ui;

import model.Book;
import model.ToRead;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a class that adds book to selected to read
public class AddBook extends JPanel implements ActionListener {
    String[] toReadCategory = {"Pleasure Reads", "Homework Reads"};
    JComboBox categoryList;
    JTextField bookTitle;
    JTextField bookAuthor;
    JButton addButton;
    JButton backButton;

    // MODIFIES: this
    // EFFECTS: Adds new book to selected category
    public AddBook() {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Select category: "));
        selectCategory();
        this.add(new JLabel("Input book title: "));
        inputBookTitle();
        this.add(new JLabel("Input book author: "));
        inputBookAuthor();
        buttonAdd();
        buttonBack();

    }

    // MODIFIES: this
    // EFFECTS: selects category to add new book
    public void selectCategory() {
        categoryList = new JComboBox(toReadCategory);
        this.add(categoryList);
    }

    // MODIFIES: this
    // EFFECTS: creates text box for book title
    public void inputBookTitle() {
        bookTitle = new JTextField(20);
        bookTitle.setToolTipText("Input book title.");
        this.add(bookTitle);

    }

    // MODIFIES: this
    // EFFECTS: creates text box for book author
    public void inputBookAuthor() {
        bookAuthor = new JTextField(20);
        this.add(bookAuthor);
    }

    // MODIFIES: this
    // EFFECTS: creates button for adding book
    public void buttonAdd() {
        addButton = new JButton("Add book");
        addButton.addActionListener(this);
        this.add(addButton);
    }

    // MODIFIES: this
    // EFFECTS: creates button for going back to the menu
    public void buttonBack() {
        backButton = new JButton("Go back");
        backButton.addActionListener(this);
        this.add(backButton);
    }

    // EFFECTS: adds book to selected category
    public void addBook() {
        ToRead category = returnToReadObject(String.valueOf(categoryList.getSelectedItem()));
        String title = bookTitle.getText();
        String author = bookAuthor.getText();
        category.addBookToRead(new Book(title, author));

        // add book to JTable
        if (String.valueOf(categoryList.getSelectedItem()) == "Pleasure Reads") {
            ViewToRead.modelForPleasure.addRow(new Object[]{title, author});
        } else {
            ViewToRead.modelForHomework.addRow(new Object[]{title, author});
        }
    }

    // EFFECTS: returns to-read object that the given string refers to
    public ToRead returnToReadObject(String s) {
        if (s == "Pleasure Reads") {
            return ViewToRead.pleasureReads;
        } else {
            return ViewToRead.homeworkReads;
        }
    }


    // EFFECTS: returns to main menu / adds a book
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addBook();
            JOptionPane.showMessageDialog(null, "Your book was added.");
        } else if (e.getSource() == backButton) {
            Menu.cl.show(Menu.contentPanel, "0");
        }
    }
}
