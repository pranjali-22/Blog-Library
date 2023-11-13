package persistence;

import model.Category;
import model.Blog;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBlog(String name, Category category, Blog blog) {
        assertEquals(name, blog.getBlogName());
        assertEquals(category, blog.getCategory());
    }
}