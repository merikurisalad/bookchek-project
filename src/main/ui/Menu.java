package ui;

import model.Book;
import model.ToRead;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Represents a class that displays main menu of reading log app
public class Menu extends JFrame implements ActionListener {
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    static JPanel contentPanel = new JPanel();
    static JPanel mainPanel = new JPanel();
    static JPanel contentPanelForMain = new JPanel();
    ViewToRead viewToRead = new ViewToRead();
    AddBook addBook = new AddBook();
    static CardLayout cl = new CardLayout();
    private static final String JSON_STORE1 = "./data/pleasureReadsGUI.json";
    private static final String JSON_STORE2 = "./data/homeworkReadsGUI.json";
    private JsonWriter jsonWriter1;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader1;
    private JsonReader jsonReader2;

    // MODIFIES: this
    // EFFECTS: constructs main menu
    public Menu() {
        createButtons();
        initPersistence();

        contentPanelForMain.setLayout(new GridLayout());
        contentPanelForMain.add(b1);
        contentPanelForMain.add(b2);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(contentPanelForMain);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(b3);
        mainPanel.add(b4);

        contentPanel.setLayout(cl);
        contentPanel.add(mainPanel, "0");
        contentPanel.add(viewToRead, "1");
        contentPanel.add(addBook, "2");
        this.setContentPane(contentPanel);
        cl.show(contentPanel, "start");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
    }

    // EFFECTS: initializes persistence objects
    private void initPersistence() {
        // saving & loading for pleasure reads
        jsonWriter1 = new JsonWriter(JSON_STORE1);
        jsonReader1 = new JsonReader(JSON_STORE1);
        // saving & loading for homework reads
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);
    }

    // EFFECTS: creates buttons
    private void createButtons() {
        ImageIcon leftButtonIcon = createImageIcon("images/notepad.png");
        ImageIcon middleButtonIcon = createImageIcon("images/books.png");
        b1 = new JButton("View to-read list", leftButtonIcon);
        b2 = new JButton("Add book", middleButtonIcon);
        b3 = new JButton("Save");
        b4 = new JButton("Load");
    }

    // EFFECTS: creates image icon
    static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Menu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    // EFFECTS: shows corresponding card (panel) / saves or loads data
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            cl.show(contentPanel, "1");
        } else if (e.getSource() == b2) {
            cl.show(contentPanel, "2");
        } else if (e.getSource() == b3) {
            saveToRead(ViewToRead.pleasureReads);
            saveToRead(ViewToRead.homeworkReads);
        } else if (e.getSource() == b4) {
            loadToRead();
        }
    }

    // EFFECTS: saves pleasure reads or homework reads to file
    private void saveToRead(ToRead toRead) {
        if (toRead.getToReadName().equals("Pleasure Reads")) {
            try {
                jsonWriter1.open();
                jsonWriter1.write(ViewToRead.pleasureReads);
                jsonWriter1.close();
                JOptionPane.showMessageDialog(null, "Pleasure Reads has been saved.");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Unable to read file: " + JSON_STORE1);
            }
        } else {
            try {
                jsonWriter2.open();
                jsonWriter2.write(ViewToRead.homeworkReads);
                jsonWriter2.close();
                JOptionPane.showMessageDialog(null, "Homework Reads has been saved.");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Unable to read file: " + JSON_STORE2);
            }
        }
    }

    // EFFECTS: loads previously saved to read list for pleasure reads and homework reads
    private void loadToRead() {
        try {
            ViewToRead.pleasureReads = jsonReader1.read();
            ViewToRead.homeworkReads = jsonReader2.read();
            JOptionPane.showMessageDialog(null, "Your data has been loaded.");
            updatePleasureReadsTable();
            updateHomeworkReadsTable();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE1);
        }
    }

    // EFFECTS: updates pleasure reads and current pleasure reads in view to read panel
    void updatePleasureReadsTable() {
        // stores list of tablet that is not updated yet
        ArrayList<String> list = new ArrayList();
        for (int i = 0; i < ViewToRead.modelForPleasure.getRowCount(); i++) {
            list.add((String) ViewToRead.modelForPleasure.getValueAt(i, 0));
        }

        // adds new books (rows) to JTable
        for (Book b : ViewToRead.pleasureReads.getToReadList()) {
            String title = b.getTitle();
            String author = b.getAuthor();
            if (!(list.contains(b.getTitle()))) {
                ViewToRead.modelForPleasure.addRow(new Object[]{title, author});
            }
        }

        // stores list of current reads that is not updated yet
        ArrayList<String> currentList = new ArrayList();
        for (int i = 0; i < ViewToRead.modelForCurrentPleasure.getRowCount(); i++) {
            currentList.add((String) ViewToRead.modelForCurrentPleasure.getValueAt(i, 0));
        }

        // adds current reads to JTable
        for (Book b : ViewToRead.pleasureReads.getCurrentRead()) {
            String title = b.getTitle();
            String author = b.getAuthor();
            if (!(currentList.contains(b.getTitle()))) {
                ViewToRead.modelForCurrentPleasure.addRow(new Object[]{title, author});
            }
        }
    }

    // EFFECTS: updates homework reads and current homework reads in view to read panel
    void updateHomeworkReadsTable() {
        // stores list of books that is not updated yet
        ArrayList<String> list = new ArrayList();
        for (int i = 0; i < ViewToRead.modelForHomework.getRowCount(); i++) {
            list.add((String) ViewToRead.modelForHomework.getValueAt(i, 0));
        }

        // adds new books (rows) to JTable
        for (Book b : ViewToRead.homeworkReads.getToReadList()) {
            String title = b.getTitle();
            String author = b.getAuthor();
            if (!(list.contains(b.getTitle()))) {
                ViewToRead.modelForHomework.addRow(new Object[]{title, author});
            }
        }

        // stores list of current reads that is not updated yet
        ArrayList<String> currentList = new ArrayList();
        for (int i = 0; i < ViewToRead.modelForCurrentHomework.getRowCount(); i++) {
            currentList.add((String) ViewToRead.modelForCurrentHomework.getValueAt(i, 0));
        }

        // adds current reads to JTable
        for (Book b : ViewToRead.homeworkReads.getCurrentRead()) {
            String title = b.getTitle();
            String author = b.getAuthor();
            if (!(currentList.contains(b.getTitle()))) {
                ViewToRead.modelForCurrentHomework.addRow(new Object[]{title, author});
            }
        }
    }

}
