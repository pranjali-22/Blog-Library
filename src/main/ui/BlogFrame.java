package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.BlogLibrary;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

public class BlogFrame extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/bloglibrary.json";
    private BlogLibrary myLibrary;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS : constructs a blog frame having a given name and size
    public BlogFrame() throws IOException {
        super("Blog Library");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 400));
        ((JPanel) getContentPane()).setBorder(new BasicBorders.MenuBarBorder(Color.blue, Color.white));
        setLayout(new FlowLayout());
        JLabel label = new JLabel("Blog Library");
        add(label);
        JLabel label2 = new JLabel("                                              ");
        add(label2);
        blogLoad();
        blogAdd();
        blogRemove();
        blogLog();
        blogClearLog();
        blogPrint();
//        addImage();
        myLibrary = new BlogLibrary("Anna's Library");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        quitMessage();
    }

    // MODIFIES : this
    // EFFECTS : clears the log in the library
    public void blogClearLog() {
        JButton btnClear = new JButton("Clear Log");
        add(btnClear);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EventLog.getInstance().clear();
                JOptionPane.showMessageDialog(null, "Log Events cleared \n");
            }
        });
    }



    // MODIFIES : this
    // EFFECTS : adds the  load  library button, by pressing the button the library is loaded
    public void blogLoad() {
        JButton btnLoad = new JButton("Load Library");
        add(btnLoad);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myLibrary = jsonReader.read();
                    JOptionPane.showMessageDialog(null, myLibrary.getUserName() + " loaded");
                } catch (IOException ee) {
                    throw new RuntimeException(ee);
                }
            }
        });
    }

    // MODIFIES : this
    // EFFECTS : adds image to the blog frame
    public void addImage() {
        ImageIcon icon = new ImageIcon("Image/blogImage.png");
        Image scaleImage = icon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon s = new ImageIcon(scaleImage);
        add(new JLabel(s));
        pack();
        setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : adds the Add button in the blog frame
    public void blogAdd() {
        JButton btnAdd = new JButton("Add Blog");
        btnAdd.setActionCommand("Add Blog");
        add(btnAdd);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddWindow(myLibrary, jsonReader, jsonWriter);
            }
        });
    }

    // MODIFIES : this
    // EFFECTS : adds the Log button in the blog frame
    public void blogLog() {
        JButton btnAdd = new JButton("Log Event");
        add(btnAdd);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLogWindow();
            }
        });
    }

    // MODIFIES : this
    // EFFECTS : adds the Remove button in the blog frame
    public void blogRemove() {
        JButton btnRemove = new JButton("Remove Blog");
        btnRemove.setActionCommand("Remove Blog");
        add(btnRemove);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRemoveWindow(myLibrary, jsonReader, jsonWriter);
            }
        });
    }


    // MODIFIES : this
    // EFFECTS : adds the Load button in the blog frame
    public void blogPrint() {
        JButton btnLoad = new JButton("Print Blogs");
        add(btnLoad);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPrintWindow(myLibrary, jsonReader, jsonWriter);
            }
        });
    }

    //This is the method that is called when the JButton btn is clicked

    // MODIFIES : this
    // EFFECTS : performs the action when the button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Blog")) {
            showAddWindow(myLibrary, jsonReader, jsonWriter);
        } else if ((e.getActionCommand().equals("Remove Blog"))) {
            showRemoveWindow(myLibrary, jsonReader, jsonWriter);
//        } else if ((e.getActionCommand().equals("Open Blog"))) {
//            showOpenWindow(myLibrary);
        } else if ((e.getActionCommand().equals("Load Blog"))) {
            showPrintWindow(myLibrary, jsonReader, jsonWriter);
        }
    }

    // This runs the program
    public static void main(String[] args) throws IOException {
        new BlogFrame();
    }

    // MODIFIES : this
    // EFFECTS : opens a new window for Add frame
    private static void showAddWindow(BlogLibrary myLibrary, JsonReader jsonReader, JsonWriter jsonWriter) {
        AddFrame addFrame = new AddFrame(myLibrary, jsonReader, jsonWriter);
        addFrame.setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : opens a new window for Remove frame
    private static void showRemoveWindow(BlogLibrary myLibrary, JsonReader jsonReader, JsonWriter jsonWriter) {
        RemoveFrame removeFrame = new RemoveFrame(myLibrary, jsonReader, jsonWriter);
        removeFrame.setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : opens a new window for Print frame
    private static void showPrintWindow(BlogLibrary myLibrary, JsonReader jsonReader, JsonWriter jsonWriter) {
        PrintFrame printFrame = new PrintFrame(myLibrary, jsonReader, jsonWriter);
        printFrame.setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : opens a new window for Log frame
    private static void showLogWindow() {
        LogFrame printFrame = new LogFrame();
        printFrame.setVisible(true);
    }

    // EFFECTS: saves the blog library to file
    private void saveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(myLibrary);
            jsonWriter.close();
            System.out.println("Saved " + myLibrary.getUserName() + " to library");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    public void quitMessage() {
            // Add a window listener to detect when the user closes the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                showQuitMessage();
                System.exit(0);
            }
        });

            // Set up the frame's content
        setSize(400, 300);
        setVisible(true);
    }

    private void showQuitMessage() {
        JOptionPane.showMessageDialog(this, "Thank you for using the program!",
                "Goodbye", JOptionPane.INFORMATION_MESSAGE);
        printLog(EventLog.getInstance());
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.getDescription());
        }

    }

}