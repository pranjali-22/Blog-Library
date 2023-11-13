package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Date;
import java.util.ArrayList;

// Represents a Blog Class. Each Blog has a given name, date when the blog was made, category of blog (travel,
// lifestyle, health), content of blog. The user can like a blog and add review about the blog.
// .
public class Blog implements Writable {
    private String blogName;
    private Category category;
    private String content;
    private int like;
    private ArrayList<String> reviews;

    // EFFECTS : constructs a blog with the given blog name, current date (date when the blog is made),
    // empty category, empty content, zero likes and zero reviews.
    public Blog(String blogName,Category category) {
        this.blogName = blogName;
        this.category = category;
        this.content = "";
        this.like = 0;
        this.reviews = new ArrayList<String>();  // class

    }

    // Getter Methods

    public String getBlogName() {
        return blogName;
    }

    public Category getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public int getLike() {
        return like;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    // EFFECTS: returns string representation of a blog with its blog name and category
    public String toString() {
        return category + ": " + blogName;
    }

    // Setter Methods

    public void setBlogName(String name) {
        this.blogName = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // MODIFIES : this
    // EFFECTS : increases the like by one
    public void addLike() {
        this.like = like + 1;
    }

    // MODIFIES : this
    // EFFECTS : decreases the like by one if like >= 0
    public void removeLike() {
        if (like > 0) {
            this.like = like - 1;
        }
    }

    // REQUIRES: review with a non-zero string length
    // MODIFIES : this
    // EFFECTS : adds review to the list of reviews
    public void addReview(String review) {
        reviews.add(review);
    }

    // EFFECTS: returns blog in the blog library as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", blogName);
        json.put("category", category);
        return json;
    }

}
