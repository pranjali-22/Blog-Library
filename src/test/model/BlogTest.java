package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BlogTest {
    private Blog testblog;

    @BeforeEach
    void runBefore() {
        testblog = new Blog("testingBlog",Category.TRAVEL);
    }

    @Test
    void testConstructor() {
        assertEquals("testingBlog", testblog.getBlogName());
        assertEquals(Category.TRAVEL, testblog.getCategory());
        assertEquals("", testblog.getContent());
        assertEquals(0, testblog.getLike());
        ArrayList<String> rev = testblog.getReviews();
        assertEquals(0, rev.size());

    }
    @Test
    void testSetBlogName() {
        testblog.setBlogName("Blog1");
        assertEquals("Blog1", testblog.getBlogName());
    }
    @Test
    void testSetCategory() {
        testblog.setCategory(Category.TRAVEL);
        assertEquals(Category.TRAVEL, testblog.getCategory());
    }
    @Test
    void testsetContent() {
        testblog.setContent("This is the first blog and is made for testing");
        assertEquals("This is the first blog and is made for testing", testblog.getContent());
    }

    @Test
    void testAddLike() {
        assertEquals(0, testblog.getLike());
        testblog.addLike();
        testblog.addLike();
        assertEquals(2, testblog.getLike());
    }

    @Test
    void testRemoveLike() {
        assertEquals(0, testblog.getLike());
        testblog.removeLike();
        assertEquals(0, testblog.getLike());
        testblog.addLike();
        testblog.addLike();
        testblog.removeLike();
        assertEquals(1, testblog.getLike());
        testblog.removeLike();
        testblog.removeLike();
        assertEquals(0, testblog.getLike());

    }

    @Test
    void testToString() {
        assertEquals("TRAVEL: testingBlog",testblog.toString());
    }

    @Test
    void testAddReview() {
        String rev1 = "Nice";
        String rev2 = "Amazing";
        String rev3 = "Good";
        ArrayList<String> rev = testblog.getReviews();
        assertEquals(0, rev.size());
        testblog.addReview(rev1);
        assertEquals(1, rev.size());
        testblog.addReview(rev2);
        testblog.addReview(rev3);
        assertEquals(3, rev.size());
    }
}