package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class main_menu_student extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String userNumber;
    LinearLayout course_list;
    DrawerLayout drawerLayout;
    TextView display;
    NavigationView navigationView;
    View view;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_student);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("information");
        course_list = (LinearLayout) findViewById(R.id.list_courses);
        display = findViewById(R.id.NoCoursesAvailable);

        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);


        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        view = navigationView.getHeaderView(0);
        userName = view.findViewById(R.id.name);
        userName.setText(userNumber);
        ImageView imageView = view.findViewById(R.id.imageView9);
        DataBase.get_image(this, userNumber, imageView);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.navigator_open,R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Course Dashboard");

        DataBase.student_courses(this, userNumber, course_list, display);
    }

    public static void out(Context context){
        Intent search = new Intent(context, MainActivity.class);
        context.startActivity(search);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.search:
                Intent search = new Intent(this, search_courses.class);
                search.putExtra("usernumber",userNumber);
                startActivity(search);
                return true;
            case R.id.profile:
                Intent profile = new Intent(this, profile.class);
                profile.putExtra("usernumber", userNumber);
                profile.putExtra("has_image", "");
                startActivity(profile);
                return true;
            case R.id.logout:
                Intent log = new Intent(this, MainActivity.class);
                startActivity(log);
                return true;
            case R.id.menu_page:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void go_to_courses(View view) {
        Intent search = new Intent(this, search_courses.class);
        search.putExtra("usernumber",userNumber);
        startActivity(search);
    }
}