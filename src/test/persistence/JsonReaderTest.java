package persistence;

import model.Category;
import model.Blog;
import model.BlogLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BlogLibrary bl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            BlogLibrary bl = reader.read();
            assertEquals("My Blog Library", bl.getUserName());
            assertEquals(0, bl.numBlogs());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        try {
            BlogLibrary bl = reader.read();
            assertEquals("My Blog Library", bl.getUserName());
            List<Blog> blogs = bl.getBlogs();
            assertEquals(2, blogs.size());
            checkBlog("Europe", Category.TRAVEL, blogs.get(0));
            checkBlog("Gym", Category.FITNESS, blogs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}