package ui;

import model.Book;
import model.QuoteBook;
import model.ToRead;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a class that displays to reads and current reads
public class ViewToRead extends JPanel implements ActionListener {
    JPanel panelPleasureReads = new JPanel();
    JPanel panelHomeworkReads = new JPanel();
    static JTable tablePleasureReads = new JTable();
    static JTable tableHomeworkReads = new JTable();
    static DefaultTableModel modelForPleasure;
    static DefaultTableModel modelForHomework;
    private Book bookN;
    private Book bookJ;
    static ToRead pleasureReads;
    static ToRead homeworkReads;
    JButton backButton = new JButton();
    JPanel currentPleasureReads = new JPanel();
    JPanel currentHomeworkReads = new JPanel();
    JTable tableCurrentPleasure = new JTable();
    JTable tableCurrentHomework = new JTable();
    static DefaultTableModel modelForCurrentPleasure;
    static DefaultTableModel modelForCurrentHomework;
    JButton addCurrentPleasure = new JButton();
    JButton addCurrentHomework = new JButton();


    // EFFECTS: creates panel with pleasure reads table and homework reads table, add book button
    public ViewToRead() {
        super();
        init();

        panelPleasureReads.setLayout(new BoxLayout(panelPleasureReads, BoxLayout.Y_AXIS));
        panelHomeworkReads.setLayout(new BoxLayout(panelHomeworkReads, BoxLayout.Y_AXIS));

        addLabels();

        createPleasureReadsTable();
        createHomeworkReadsTable();

        createSplitPlane();

        addCurrentLabels();

        buttonAddCurrentRead();

        createCurrentPleasureReadsTable();
        createCurrentHomeworkReadsTable();

        panelPleasureReads.add(currentPleasureReads);
        panelHomeworkReads.add(currentHomeworkReads);

        buttonBack();

    }

    // MODIFIES: bookN, bookJ, pleasureReads, homeworkReads
    // EFFECTS: initializes pleasure reads and homework reads to contain some data (for demonstration)
    private void init() {
        // initializes book objects
        bookN = new Book("No Longer Human", "Osamu Dazai");
        bookJ = new Book("Jane Eyre", "Charlotte Bronte");

        // initializes to read objects
        pleasureReads = new ToRead("Pleasure Reads");
        homeworkReads = new ToRead("Homework Reads");
        pleasureReads.addBookToRead(bookN);
        homeworkReads.addBookToRead(bookJ);

    }

    // MODIFIES: panelPleasureReads, panelHomeworkReads
    // EFFECTS: adds explanatory JLabels for pleasure reads and homework reads
    private void addLabels() {
        JLabel pleasureReadsLabel = new JLabel();
        JLabel homeworkReadsLabel = new JLabel();

        pleasureReadsLabel.setText("HERE ARE YOUR PLEASURE READS: ");
        homeworkReadsLabel.setText("HERE ARE YOUR HOMEWORK READS: ");

        panelPleasureReads.add(pleasureReadsLabel);
        panelHomeworkReads.add(homeworkReadsLabel);
    }

    // MODIFIES: currentPleasureReads, currentHomeworkReads
    // EFFECTS: adds explanatory JLabels for current reads for pleasure reads and homework reads
    private void addCurrentLabels() {
        JLabel currentPleasureReadsLabel = new JLabel();
        JLabel currentHomeworkReadsLabel = new JLabel();

        currentPleasureReadsLabel.setText("CURRENT PLEASURE READS: ");
        currentHomeworkReadsLabel.setText("CURRENT HOMEWORK READS: ");

        currentPleasureReads.add(currentPleasureReadsLabel);
        currentHomeworkReads.add(currentHomeworkReadsLabel);
    }


    // MODIFIES: this
    // EFFECTS: splits plane so pleasure reads panel is set on the left and homework reads panel on the right
    private void createSplitPlane() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(panelPleasureReads);
        splitPane.setRightComponent(panelHomeworkReads);
        this.add(splitPane);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: instantiates button that returns user to main menu
    public void buttonBack() {
        backButton = new JButton("Go back");
        backButton.addActionListener(this);
        this.add(backButton);
    }

    // MODIFIES: tablePleasureReads, modelForPleasure, panelPleasureReads
    // EFFECTS: creates JTable for pleasure reads
    public void createPleasureReadsTable() {
        tablePleasureReads = new JTable();
        modelForPleasure = new DefaultTableModel();
        String[] columnNames = new String[]{"Title", "Author"};
        modelForPleasure.setColumnIdentifiers(columnNames);
        for (Book b : pleasureReads.getToReadList()) {
            modelForPleasure.addRow(new Object[]{b.getTitle(), b.getAuthor()});
        }

        JTableHeader header = tablePleasureReads.getTableHeader();
        header.setForeground(Color.blue);
        header.setBackground(Color.white);
        tablePleasureReads.setModel(modelForPleasure);
        panelPleasureReads.add(new JScrollPane(tablePleasureReads));

        tablePleasureReads.setRowSelectionAllowed(true);
        tablePleasureReads.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // MODIFIES: tablePleasureReads, modelForPleasure, panelPleasureReads
    // EFFECTS: creates JTable for pleasure reads
    public void createHomeworkReadsTable() {
        tableHomeworkReads = new JTable();
        modelForHomework = new DefaultTableModel();
        String[] columnNames = new String[]{"Title", "Author"};
        modelForHomework.setColumnIdentifiers(columnNames);
        for (Book b : homeworkReads.getToReadList()) {
            modelForHomework.addRow(new Object[]{b.getTitle(), b.getAuthor()});
        }

        JTableHeader header = tableHomeworkReads.getTableHeader();
        header.setForeground(Color.blue);
        header.setBackground(Color.white);
        tableHomeworkReads.setModel(modelForHomework);
        panelHomeworkReads.add(new JScrollPane(tableHomeworkReads));

        tableHomeworkReads.setRowSelectionAllowed(true);
        tableHomeworkReads.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // MODIFIES: currentPleasureReads
    // EFFECTS: instantiates current pleasure reads table
    private void createCurrentPleasureReadsTable() {
        tableCurrentPleasure = new JTable();
        modelForCurrentPleasure = new DefaultTableModel();
        String[] columnNames = new String[]{"Title", "Author"};
        modelForCurrentPleasure.setColumnIdentifiers(columnNames);

        JTableHeader header = tableCurrentPleasure.getTableHeader();
        header.setForeground(Color.blue);
        header.setBackground(Color.white);
        tableCurrentPleasure.setModel(modelForCurrentPleasure);
        currentPleasureReads.add(new JScrollPane(tableCurrentPleasure));

    }

    // MODIFIES: currentHomeworkReads
    // EFFECTS: instantiates table for current homework
    private void createCurrentHomeworkReadsTable() {
        tableCurrentHomework = new JTable();
        modelForCurrentHomework = new DefaultTableModel();
        String[] columnNames = new String[]{"Title", "Author"};
        modelForCurrentHomework.setColumnIdentifiers(columnNames);

        JTableHeader header = tableCurrentHomework.getTableHeader();
        header.setForeground(Color.blue);
        header.setBackground(Color.white);
        tableCurrentHomework.setModel(modelForCurrentHomework);
        currentHomeworkReads.add(new JScrollPane(tableCurrentHomework));
    }

    // MODIFIES: panelPleasureReads, panelHomeworkReads
    // EFFECTS: creates button that adds a book to current read
    private void buttonAddCurrentRead() {
        addCurrentPleasure = new JButton("Add selected row to current reads");
        addCurrentHomework = new JButton("Add selected row to current reads");
        addCurrentPleasure.addActionListener(this);
        addCurrentHomework.addActionListener(this);

        panelPleasureReads.add(addCurrentPleasure);
        panelHomeworkReads.add(addCurrentHomework);
    }


    // MODIFIES: pleasureReads, modelForCurrentPleasure
    // EFFECTS: adds selected book to current pleasure reads
    private void addCurrentPleasureRead() {
        Integer row = tablePleasureReads.getSelectedRow();
        String title = (String) tablePleasureReads.getValueAt(row, 0);
        String author = (String) tablePleasureReads.getValueAt(row, 1);
        Book book = new Book(title, author);
        pleasureReads.addCurrentRead(book);

        // add book to JTable
        modelForCurrentPleasure.addRow(new Object[]{title, author});
    }

    // MODIFIES: homeworkReads, modelForCurrentHomework
    // EFFECTS: adds selected book to current homework reads
    private void addCurrentHomeworkRead() {
        Integer row = tableHomeworkReads.getSelectedRow();
        String title = (String) tableHomeworkReads.getValueAt(row, 0);
        String author = (String) tableHomeworkReads.getValueAt(row, 1);
        Book book = new Book(title, author);
        homeworkReads.addCurrentRead(book);

        // add book to JTable
        modelForCurrentHomework.addRow(new Object[]{title, author});

    }

    // EFFECTS: returns to main menu / adds current read
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCurrentPleasure) {
            addCurrentPleasureRead();
        } else if (e.getSource() == addCurrentHomework) {
            addCurrentHomeworkRead();
        } else if (e.getSource() == backButton) {
            Menu.cl.show(Menu.contentPanel, "0");
        }

    }

}
