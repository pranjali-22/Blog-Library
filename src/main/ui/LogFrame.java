package ui;

import model.BlogLibrary;
import model.EventLog;
import model.Event;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static java.awt.SystemColor.desktop;

public class LogFrame extends JFrame {

    // EFFECTS : constructs a remove frame having a given name and size
    public LogFrame() {
        super("Log Events");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setVisible(true);
        setLayout(new FlowLayout());
        printLog(EventLog.getInstance());
//        desktop.add((ScreenPrinter) lp);
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            JLabel nameLabel = new JLabel(next.getDescription());
            add(nameLabel);
        }

    }
}