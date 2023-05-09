package com.example.wits_academy;

import static org.junit.Assert.*;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class forgot_passwordTest {

    @Rule
    public ActivityTestRule<forgot_password> mActivityRule = new ActivityTestRule(forgot_password.class);
    private forgot_password forgot_password;

    @Before
    public void setUp() throws Exception {
        forgot_password = mActivityRule.getActivity();
    }

    @Test
    public void Test(){
        assertNotNull(forgot_password.user_number);
        assertNotNull(forgot_password.email);
        assertNotNull(forgot_password.new_password);
        assertNotNull(forgot_password.confirm_new_password);
        assertNotNull(forgot_password.role);
    }

    @After
    public void tearDown() throws Exception {
        forgot_password = null;
    }
}