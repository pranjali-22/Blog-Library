package ui;

import model.Blog;
import model.Category;
import model.BlogLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


// Blog Platform application
public class BlogApp {
    private static final String JSON_STORE = "./data/bloglibrary.json";
    private Scanner input;
    private BlogLibrary myLibrary;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs bloglibrary and runs application
    public BlogApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        myLibrary = new BlogLibrary("Anna's Library");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBlogLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBlogLibrary() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add blog");
        System.out.println("\tr -> remove blog");
        System.out.println("\to -> open blog");
        System.out.println("\tc -> categorise blogs");
        System.out.println("\tp -> print blogs");
        System.out.println("\ts -> save blog library to file");
        System.out.println("\tl -> load blog library from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addBlog();
        } else if (command.equals("r")) {
            removeBlog();
        } else if (command.equals("o")) {
            openBlog();
        } else if (command.equals("c")) {
            categoriseBlogs();
        } else if (command.equals("p")) {
            printBlogs();
        } else if (command.equals("s")) {
            saveLibrary();
        } else if (command.equals("l")) {
            loadLibrary();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes Blog Library
//    public void init() {
//        System.out.println("Enter user name");
//        input = new Scanner(System.in);
//        String userName = input.next();
//        myLibrary = new BlogLibrary(userName);
//    }

    // MODIFIES: this
    // EFFECTS: adds a blog to the library
    private void addBlog() {
        Category category = readCategory();
        System.out.println("Please enter name of blog: ");
        String name = input.next();

        System.out.print("Enter the blog content ");
        String content = input.next();

        myLibrary.addBlog(new Blog(name, category));
        System.out.println(name + " Blog added to your library");
    }

    // EFFECTS: prompts user to select category and returns it
    private Category readCategory() {
        System.out.println("Please select a category for your blog");

        int menuLabel = 1;
        for (Category c : Category.values()) {
            System.out.println(menuLabel + ": " + c);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return Category.values()[menuSelection - 1];
    }

    // EFFECTS: prints all the blogs in blog library to the console
    private void printBlogs() {
        List<Blog> blogs = myLibrary.getBlogs();
        for (Blog t : blogs) {
            System.out.println(t);
        }
    }

    // EFFECTS: saves the blog library to file
    private void saveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(myLibrary);
            jsonWriter.close();
            System.out.println("Saved " + myLibrary.getUserName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads blog library from file
    private void loadLibrary() {
        try {
            myLibrary = jsonReader.read();
            System.out.println("Loaded " + myLibrary.getUserName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a blog from the library
    private void removeBlog() {
        System.out.print("Enter the blog name: ");
        String name = input.next();
        myLibrary.removeblog(name);
        System.out.println(name + " Blog removed from your library");
    }


     // MODIFIES: this
     // EFFECTS: categorise the blogs by given  category in the library
    private void categoriseBlogs() {
        Category category = readCategory();
        ArrayList<Blog> blogs = myLibrary.categoriseBlogs(category);
        for (Blog b : blogs) {
            System.out.println("Blog name : " + b.getBlogName());
            System.out.println("Blog category : " + b.getCategory());
        }
    }

    // EFFECTS: opens the blog with given blog name from the library
    //          asks user for a like and review on the opened blog

    private void openBlog() {
        System.out.print("Enter the blog name: ");
        String name = input.next();
        Blog rblog = myLibrary.openblog(name);
        System.out.println("Blog name : " + rblog.getBlogName());
//        System.out.println("Date : " + rblog.getDate());
        System.out.println("Category : " + rblog.getCategory());
        System.out.println("Content : " + rblog.getContent());
        System.out.println("Likes : " + rblog.getLike());
        System.out.println("Reviews : " + rblog.getReviews());
        System.out.println("Do you want to like and review the blog? (Y/N)");
        String answer = input.next();
        if (answer.equals("Y")) {
            System.out.println("Enter you review");
            String rev = input.next();
            rblog.addReview(rev);
            rblog.addLike();
        }
        System.out.println("Blog name : " + rblog.getBlogName());
//        System.out.println("Date : " + rblog.getDate());
        System.out.println("Category : " + rblog.getCategory());
        System.out.println("Content : " + rblog.getContent());
        System.out.println("Likes : " + rblog.getLike());
        System.out.println("Reviews : " + rblog.getReviews());
    }

}








