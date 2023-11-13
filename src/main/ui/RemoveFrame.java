package ui;

import model.BlogLibrary;
import model.Event;
import model.EventLog;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class RemoveFrame extends JFrame {
    private static final String JSON_STORE = "./data/bloglibrary.json";
    private JFrame removeFrame;

    // EFFECTS : constructs a remove frame having a given name and size
    public RemoveFrame(BlogLibrary myLibrary, JsonReader jsonReader, JsonWriter jsonWriter) {
        super("Remove Blog");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setVisible(true);
        setLayout(new FlowLayout());
        removingBlog(myLibrary);
        saveBlog(myLibrary, jsonReader, jsonWriter);
        addImage();
    }

    // MODIFIES : this
    // EFFECTS : adds image to the frame
    public void addImage() {
        ImageIcon icon = new ImageIcon("Image/blogImage2.png");
        Image scaleImage = icon.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
        ImageIcon s = new ImageIcon(scaleImage);
        add(new JLabel(s));
        setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : adds a remove button, label button and a button for taking blog name
    public void removingBlog(BlogLibrary myLibrary) {
        JLabel nameLabel = new JLabel("Blog Name:");
        JTextField nameTextField = new JTextField(20);
        JButton removeButton = new JButton("Remove");
        add(nameLabel);
        add(nameTextField);
        add(removeButton);
        removeButton.addActionListener(new ActionListener() {

            // EFFECTS: removes the blog from the library
            @Override
            public void actionPerformed(ActionEvent e) {
                String blogName = nameTextField.getText();
                JOptionPane.showMessageDialog(removeFrame, "Blog: " + blogName + " removed from your library");
                myLibrary.removeblog(blogName);
                EventLog.getInstance().logEvent(new Event("Blog: " + blogName + " removed from library \n"));
            }
        });
    }

    // EFFECTS : removes the blogs from the JSON object
    public void saveBlog(BlogLibrary myLibrary, JsonReader jsonReader, JsonWriter jsonWriter) {
        JButton saveBlog = new JButton("Save");
        add(saveBlog);
        saveBlog.setActionCommand("Save");

        saveBlog.addActionListener(new ActionListener() {

            // EFFECTS: removes the blog from the library
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(myLibrary);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(removeFrame, "Saved: " + myLibrary.getUserName() + " to "
                            + JSON_STORE);
                } catch (FileNotFoundException ee) {
                    JOptionPane.showMessageDialog(removeFrame, "Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }
}