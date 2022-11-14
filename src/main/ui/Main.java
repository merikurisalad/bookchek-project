package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


// runs gui of reading log app
public class Main {
    static Menu menu;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                menu = new Menu();
                menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menu.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        for (Event event : EventLog.getInstance()) {
                            System.out.println(event.getDescription());
                        }
                        e.getWindow().dispose();
                    }
                });
                menu.pack();
                menu.setVisible(true);
            }
        });
    }
}