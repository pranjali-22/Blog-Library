package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BlogLibraryTest {
    private BlogLibrary testbloglibrary;

    @BeforeEach
    void runBefore() {
        testbloglibrary = new BlogLibrary("testingLibrary");
    }

    @Test
    void testConstructor() {
        assertEquals("testingLibrary", testbloglibrary.getUserName());
        assertEquals(0, testbloglibrary.numBlogs());
    }

    @Test
    void testSetUserName() {
        testbloglibrary.setUserName("Testlib1");
        assertEquals("Testlib1", testbloglibrary.getUserName());
    }

    @Test
    void testAddBlog() {
        Blog blog1 = new Blog("blog1",Category.TRAVEL);
        Blog blog2 = new Blog("blog2",Category.TRAVEL);
        Blog blog3 = new Blog("blog3",Category.FITNESS);

        ArrayList<Blog> sblogs = testbloglibrary.getSavedBlogs();
        assertEquals(0, sblogs.size());
        testbloglibrary.addBlog(blog1);
        assertEquals(1, sblogs.size());
        assertTrue(sblogs.contains(blog1));
        testbloglibrary.addBlog(blog2);
        testbloglibrary.addBlog(blog3);
        assertEquals(3, sblogs.size());
        assertTrue(sblogs.contains(blog2));
        assertTrue(sblogs.contains(blog3));
    }

    @Test
    void testRemoveBlog() {
        Blog blog1 = new Blog("blog1",Category.TRAVEL);
        Blog blog2 = new Blog("blog2",Category.TRAVEL);
        Blog blog3 = new Blog("blog3",Category.FITNESS);
        Blog blog4 = new Blog("blog4",Category.BUSINESS);
        ArrayList<Blog> sblogs = testbloglibrary.getSavedBlogs();
        assertEquals(0, sblogs.size());
        testbloglibrary.addBlog(blog1);
        assertEquals(1, sblogs.size());
        testbloglibrary.removeblog("blog1");
        assertEquals(0, sblogs.size());
        testbloglibrary.addBlog(blog1);
        testbloglibrary.addBlog(blog2);
        testbloglibrary.addBlog(blog3);
        testbloglibrary.removeblog("blog2");
        assertEquals(2, sblogs.size());
        assertFalse(sblogs.contains(blog2));
        testbloglibrary.removeblog("blog4");
        assertEquals(2, sblogs.size());
    }
    @Test
    void testOpenBlog() {
        Blog blog1 = new Blog("blog1",Category.TRAVEL);
        Blog blog2 = new Blog("blog2",Category.TRAVEL);
        Blog blog3 = new Blog("blog3",Category.FITNESS);
        testbloglibrary.addBlog(blog2);
        assertEquals(blog2,testbloglibrary.openblog(blog2.getBlogName()));
        assertEquals(null,testbloglibrary.openblog(blog3.getBlogName()));

    }

    @Test
    void testCategoriseBlog() {
        Blog blog1 = new Blog("blog1",Category.TRAVEL);
        Blog blog2 = new Blog("blog2",Category.TRAVEL);
        Blog blog3 = new Blog("blog3",Category.FITNESS);
        testbloglibrary.addBlog(blog1);
        testbloglibrary.addBlog(blog2);
        testbloglibrary.addBlog(blog3);
        ArrayList<Blog> catblog = testbloglibrary.categoriseBlogs(Category.TRAVEL);
        assertTrue(catblog.contains(blog1));
        assertTrue(catblog.contains(blog2));
        assertFalse(catblog.contains(blog3));
    }

}

