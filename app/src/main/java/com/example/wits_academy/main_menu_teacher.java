package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class main_menu_teacher extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String userNumber;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    TextView logout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_teacher);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("information");
        course_list = (LinearLayout) findViewById(R.id.t_courses);

        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);


        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView userName = view.findViewById(R.id.name);
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

        DataBase.teacher_courses(this, userNumber, course_list);

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
            case R.id.create:
                Intent search = new Intent(this, create_course.class);
                search.putExtra("usernumber",userNumber);
                startActivity(search);
                return true;
            case R.id.profile:
                Intent profile = new Intent(this, profile.class);
                profile.putExtra("usernumber", userNumber);
                profile.putExtra("has_image", "ghhhh");
                startActivity(profile);
                return true;
            case R.id.logout:
                Intent log = new Intent(this, MainActivity.class);
                startActivity(log);
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}