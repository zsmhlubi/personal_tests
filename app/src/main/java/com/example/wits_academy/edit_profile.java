package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class edit_profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText name;
    EditText surname;
    EditText email;
    EditText userNumber;
    Intent user_number;
    ImageView userImage;
    Intent Image;
    String has = "";
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        user_number = getIntent();
        name = (EditText) findViewById(R.id.profile_name);
        surname = (EditText) findViewById(R.id.profile_surname);
        email = (EditText) findViewById(R.id.profile_email);
        userNumber = (EditText) findViewById(R.id.profile_number);
        userImage = (ImageView) findViewById(R.id.imageView2);

        name.setHint(user_number.getStringExtra("name"));
        userNumber.setHint(user_number.getStringExtra("userNumber"));
        surname.setHint(user_number.getStringExtra("surname"));
        email.setHint(user_number.getStringExtra("email"));

        Picasso.get()
                .load("http://10.203.198.18/wits/php/profile_photos/" + user_number.getStringExtra("userNumber") + ".jpg")
                .error(R.drawable.profile_icon)
                .fit()
                .into(userImage);

        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);



        TextView userName = view.findViewById(R.id.name);
        userName.setText(user_number.getStringExtra("name") + " " + user_number.getStringExtra("surname"));
        ImageView imageView = view.findViewById(R.id.imageView9);
        Picasso.get()
                .load("http://10.203.197.211/wits/php/profile_photos/" + userNumber + ".jpg")
                .error(R.drawable.profile_icon)
                .fit()
                .into(imageView);

        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_menu_student.out(edit_profile.this);
            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle(user_number.getStringExtra("name") + " " + user_number.getStringExtra("surname"));

        if (user_number.getStringExtra("has_image").isEmpty()){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.student_toolbox);
        }
        else{
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.teacher_toolbox);
        }

    }

    public void confirm(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("surname", surname.getText().toString());
        map.put("email", email.getText().toString());
        map.put("user_number", userNumber.getText().toString());
        map.put("pre_userNumber", user_number.getStringExtra("userNumber"));

        DataBase.change_profile(this, map);
    }

    public void back_to_login(View view) {
        Intent intent = new Intent(this, profile.class);
        intent.putExtra("usernumber",user_number.getStringExtra("userNumber"));
        intent.putExtra("image", Image);
        intent.putExtra("has_image", has);
        startActivity(intent);
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
                search.putExtra("usernumber",userNumber.getText().toString());
                startActivity(search);
                return true;
            case R.id.create:
                Intent create = new Intent(this, create_course.class);
                create.putExtra("usernumber",userNumber.getText().toString());
                startActivity(create);
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}