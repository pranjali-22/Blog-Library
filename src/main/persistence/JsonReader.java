package persistence;

import model.Blog;
import model.Category;
import model.BlogLibrary;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Blog Library from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads blog library from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public BlogLibrary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBlogLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses blog library from JSON object and returns it
    private BlogLibrary parseBlogLibrary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        BlogLibrary bl = new BlogLibrary(name);
        addBlogLibraries(bl, jsonObject);
        return bl;
    }

    // MODIFIES: bl
    // EFFECTS: parses blogs from JSON object and adds them to blog library
    private void addBlogLibraries(BlogLibrary bl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("blogs");
        for (Object json : jsonArray) {
            JSONObject nextBlog = (JSONObject) json;
            addBlog(bl, nextBlog);
        }
    }

    // MODIFIES: bl
    // EFFECTS: parses blog from JSON object and adds it to blog library
    private void addBlog(BlogLibrary bl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Category category = Category.valueOf(jsonObject.getString("category"));
        Blog blog = new Blog(name,category);
        bl.addBlog(blog);
    }
}