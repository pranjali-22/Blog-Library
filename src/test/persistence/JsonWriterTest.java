package persistence;

import model.Category;
import model.Blog;
import model.BlogLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            BlogLibrary bl = new BlogLibrary("My blog library");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            BlogLibrary bl = new BlogLibrary("My Blog Library");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLibrary.json");
            bl = reader.read();
            assertEquals("My Blog Library", bl.getUserName());
            List<Blog> blogs = bl.getBlogs();
            assertEquals(0, blogs.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLibrary() {
        try {
            BlogLibrary bl = new BlogLibrary("My Blog Library");
            bl.addBlog(new Blog("Europe", Category.TRAVEL));
            bl.addBlog(new Blog("Gym", Category.FITNESS));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            bl = reader.read();
            assertEquals("My Blog Library", bl.getUserName());
            List<Blog> thingies = bl.getBlogs();
            assertEquals(2, thingies.size());
            checkBlog("Europe", Category.TRAVEL, thingies.get(0));
            checkBlog("Gym", Category.FITNESS, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}