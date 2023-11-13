package ui;

import model.Blog;
import model.BlogLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PrintFrame extends JFrame {

    // EFFECTS : constructs a print frame having a given name and size, prints all the blogs in the library
    public PrintFrame(BlogLibrary myLibrary, JsonReader jsonReader, JsonWriter jsonWriter) {
        super("Print Blogs");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setVisible(true);
        setLayout(new FlowLayout());
        for (Blog i : myLibrary.getBlogs()) {
            JLabel nameLabel = new JLabel("Blog Name: " + i.getBlogName());
            JLabel categoryLabel = new JLabel("Blog Category: " + i.getCategory());
            JLabel contentLabel = new JLabel("Blog Content: " + i.getContent());
            add(nameLabel);
            add(categoryLabel);
            add(contentLabel);
        }
    }
}

