package com.example.wits_academy;

import static org.junit.Assert.*;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    private MainActivity login;

    @Before
    public void setUp() throws Exception {
        login = mActivityRule.getActivity();
    }

    @Test
    public void validate_input() {
        assertNotNull(login.user_id.getText());
        assertNotNull(login.user_password.getText());
    }

    @Test
    public void main_page() {
        assertNotNull(login.user_id.getText());
        assertNotNull(login.user_password.getText());
    }

    @After
    public void tearDown() throws Exception {
        login = null;
    }


}