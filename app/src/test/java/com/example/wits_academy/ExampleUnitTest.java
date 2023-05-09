package com.example.wits_academy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        assertNotNull(MainActivity.user_id.getText());
        assertNotNull(MainActivity.user_password.getText());
    }

    @Test
    public void addition_isNotCorrect() {
        assertEquals(3, 2 + 2);
    }
}