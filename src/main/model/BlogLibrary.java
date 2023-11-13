package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a Blog Library for a user. Each blog library has a username and a collection of saved blogs by the user.
// The user can add a new blog to the library, remove a blog from the library, open a blog from the library, access a
// set of blogs categorised by category of blog , can like and add a review to the blog.

public class BlogLibrary implements Writable {
    private String username;
    private ArrayList<Blog> savedBlogs;

    // EFFECTS : constructs a BlogLibrary with the given username and forms an empty set
    //              of Blogs for savedBlogs.
    public BlogLibrary(String userName) {
        this.username = userName;
        this.savedBlogs = new ArrayList<Blog>();
    }

    //setters
    public void setUserName(String userName) {
        this.username = userName;
    }

    // getters
    public String getUserName() {
        return username;
    }

    public ArrayList<Blog> getSavedBlogs() {
        return savedBlogs;
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Blog> getBlogs() {
        return Collections.unmodifiableList(savedBlogs);
    }

    // REQUIRES : a blog having a blogName of non-zero string length
    // MODIFIES : this
    // EFFECTS : adds blog to the savedBlogs
    public void addBlog(Blog blog) {
        savedBlogs.add(blog);
    }

    // EFFECTS: returns number of blogs in the blog library
    public int numBlogs() {
        return savedBlogs.size();
    }

    // REQUIRES : a blog having a blogName of non-zero string length
    // MODIFIES : this
    // EFFECTS : removes blog to the savedBlogs

    public void removeblog(String blogName) {
        Blog req = new Blog("",Category.TRAVEL);
        for (Blog b : savedBlogs) {
            if (blogName.equals(b.getBlogName())) {
                req = b;
            }
        }
        if (req.getBlogName().equals("")) {
            savedBlogs = savedBlogs;
        } else {
            savedBlogs.remove(req);
        }
    }

    // REQUIRES: a blog having a blogName of non-zero string length
    // EFFECTS: open the blog with given name
    public Blog openblog(String blogName) {
        Blog blog = null;
        for (Blog b : savedBlogs) {
            if (blogName.equals(b.getBlogName())) {
                blog = b;
            }
        }
        return blog;
    }

    // REQUIRES: a string category having a category
    // EFFECTS : returns a list of blogs having the given category from the savedBlogs

    public ArrayList<Blog> categoriseBlogs(Category category) {
        ArrayList<Blog> categoriseBlogs;
        categoriseBlogs = new ArrayList<Blog>();
        for (Blog b : savedBlogs) {
            if (category.equals(b.getCategory())) {
                categoriseBlogs.add(b);
            }
        }
        return categoriseBlogs;
    }

    // EFFECTS: returns blogs in the blog library as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", username);
        json.put("blogs", blogstoJason());
        return json;
    }

    // EFFECTS: returns blogs in the blog library as a JSON array
    private JSONArray blogstoJason() {
        JSONArray jsonArray = new JSONArray();

        for (Blog t : savedBlogs) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}
