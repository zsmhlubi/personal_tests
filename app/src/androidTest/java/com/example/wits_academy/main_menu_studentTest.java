package com.example.wits_academy;

import static org.junit.Assert.*;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.navigation.NavigationView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class main_menu_studentTest {

    @Rule
    public ActivityTestRule<main_menu_student> mActivityRule = new ActivityTestRule(main_menu_student.class);
    private main_menu_student student;

    @Before
    public void setUp() throws Exception {
        student = mActivityRule.getActivity();
    }

    @Test
    public void Test() throws Exception{
        assertNotNull(student.course_list);
        assertNotNull(student.drawerLayout);
        assertNotNull(student.display);
        assertNotNull(student.navigationView);
        assertNotNull(student.view);
        assertNotNull(student.userName);
    }

    @After
    public void tearDown() throws Exception {
        student = null;
    }
    
}