package com.example.wits_academy;

import static org.junit.Assert.*;

import android.widget.EditText;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class register_pageTest {
    @Rule
    public ActivityTestRule<register_page> mActivityRule = new ActivityTestRule(register_page.class);
    private register_page register_page;

    @Before
    public void setUp() throws Exception {
        register_page = mActivityRule.getActivity();
    }

    @Test
    public void registerTest(){
        assertNotNull(register_page.user_number);
        assertNotNull(register_page.user_name);
        assertNotNull(register_page.user_last_name);
        assertNotNull(register_page.user_email);
        assertNotNull(register_page.create_password);
        assertNotNull(register_page.confirm_password);
        assertNotNull(register_page.string);
        assertNotNull(register_page.role);
    }

    @After
    public void tearDown() throws Exception {
        register_page = null;
    }
}