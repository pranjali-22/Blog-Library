package ui;

import model.Blog;
import model.BlogLibrary;
import model.Category;
import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.FileNotFoundException;

public class AddFrame extends JFrame {

    private Category categoryAdd;
    private static final String JSON_STORE = "./data/bloglibrary.json";
    private JFrame addFrame;

    // EFFECTS : constructs an add frame having a given name, size and buttons
    public AddFrame(BlogLibrary myLibrary, JsonReader jsonReader,JsonWriter jsonWriter) {
        JFrame addFrame = new JFrame("Add Blog");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());
        setVisible(true);
        setLayout(new FlowLayout());
        blogTravel();
        blogFitness();
        blogLifestyle();
        blogBusiness();
        addingBlog(myLibrary);
        saveBlog(myLibrary,jsonReader,jsonWriter);
    }

    // MODIFIES : this
    // EFFECTS : adds image to the frame
    public void addImage() {
        ImageIcon icon = new ImageIcon("Image/blogImage2.png");
        Image scaleImage = icon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        ImageIcon s = new ImageIcon(scaleImage);
        add(new JLabel(s));
        setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : creates a save button
    public void saveBlog(BlogLibrary myLibrary,JsonReader jsonReader,JsonWriter jsonWriter)  {
        JButton saveBlog =  new JButton("Save");
        add(saveBlog);
        saveBlog.addActionListener(new ActionListener() {

            // EFFECTS: adds the blogs to the JSON object
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(myLibrary);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(addFrame,"Saved: " + myLibrary.getUserName() + " to "
                            + JSON_STORE);

                } catch (FileNotFoundException ee) {
                    JOptionPane.showMessageDialog(addFrame,"Unable to write to file: " + JSON_STORE);
                }
            }
        });

    }

    // MODIFIES : this
    // EFFECTS : adds an add button, nameLabel button,categoryLabel button, nameTextField button, content button
    //          and adds the blog to the library
    public void addingBlog(BlogLibrary myLibrary) {
        JLabel categoryLabel = new JLabel("Blog Category");
        add(categoryLabel);
        JLabel nameLabel = new JLabel("Blog Name:");
        add(nameLabel);
        JTextField nameTextField = new JTextField(20);
        add(nameTextField);
        JLabel contentLabel = new JLabel("Blog Content:");
        JTextArea contentTextArea = new JTextArea(5, 20);
        add(contentLabel);
        add(contentTextArea);
        JButton addButton = new JButton("Add");
        add(addButton);
        addButton.addActionListener(new ActionListener() {

            // EFFECTS: adds the blogs to the library
            @Override
            public void actionPerformed(ActionEvent e) {
                String blogName = nameTextField.getText();
                JOptionPane.showMessageDialog(addFrame,"Blog: " + blogName + " added to  library");
                Blog newBlog = new Blog(blogName, categoryAdd);
                newBlog.setContent(contentTextArea.getText());
                myLibrary.addBlog(newBlog);
                EventLog.getInstance().logEvent(new Event("Blog: " + blogName + " added to  library\n"));
            }
        });


    }

    // MODIFIES : this
    // EFFECTS : adds a travel button to the frame

    public void blogTravel() {
        JButton travelButton = new JButton("TRAVEL");
        add(travelButton);
        travelButton.setActionCommand("TRAVEL");
        travelButton.addActionListener(new ActionListener() {

            // EFFECTS: sets the category to FITNESS when the button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("TRAVEL")) {
                    categoryAdd = Category.TRAVEL;
                }
            }
        });
    }

    // MODIFIES : this
    // EFFECTS : adds a fitness button to the frame
    public void blogFitness() {
        JButton fitnessButton = new JButton("FITNESS");
        add(fitnessButton);
        fitnessButton.setActionCommand("FITNESS");
        fitnessButton.addActionListener(new ActionListener() {

            // EFFECTS: sets the category to FITNESS when the button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("FITNESS")) {
                    categoryAdd = Category.FITNESS;
                }
            }
        });
    }

    // MODIFIES : this
    // EFFECTS : adds a lifestyle button to the frame
    public void blogLifestyle() {
        JButton lifestyleButton = new JButton("LIFESTYLE");
        add(lifestyleButton);
        lifestyleButton.setActionCommand("LIFESTYLE");
        lifestyleButton.addActionListener(new ActionListener() {

            // EFFECTS: sets the category to LIFESTYLE when the button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("LIFESTYLE")) {
                    categoryAdd = Category.LIFESTYLE;
                }
            }
        });
    }

    // MODIFIES : this
    // EFFECTS : adds a business button to the frame
    public void blogBusiness() {
        JButton businessButton = new JButton("BUSINESS");
        add(businessButton);
        businessButton.setActionCommand("BUSINESS");
        businessButton.addActionListener(new ActionListener() {

            // EFFECTS: sets the category to BUSINESS when the button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("BUSINESS")) {
                    categoryAdd = Category.BUSINESS;
                }
            }
        });
    }

}