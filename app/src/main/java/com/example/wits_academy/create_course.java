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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class create_course extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener{

    EditText name;
    EditText code;
    EditText school;
    EditText faculty;
    Integer year;
    EditText password;
    EditText c_password;
    String userNumber;
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_course);

        //changing background and title on toolbar


        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("usernumber");
        name = (EditText) findViewById(R.id.course_name);
        code =(EditText) findViewById(R.id.course_code);
        school = (EditText) findViewById(R.id.course_school);
        faculty = (EditText) findViewById(R.id.course_faculty);
        password = (EditText) findViewById(R.id.course_password);
        c_password = (EditText) findViewById(R.id.confirm_c_password);

        Spinner spinner = findViewById(R.id.year_of_study);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);

        TextView userName = view.findViewById(R.id.name);
        userName.setText(userNumber);
        ImageView imageView = view.findViewById(R.id.imageView9);
        DataBase.get_image(this, userNumber, imageView);

        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Create");

    }

    public void create(View view) {
        if (!filled_in()) {
            return;
        }
        else {
            adding_to_databasa();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String string = parent.getItemAtPosition(position).toString();
        if (string.equals("1 - First Year")){
            year = 1;
        }
        else if (string.equals("2 - Second Year")){
            year = 2;
        }
        else if (string.equals("3 - Third Year")){
            year = 3;
        }
        else if (string.equals("4 - Forth Year")){
            year = 4;
        }
        else if (string.equals("5 - Fifth Year")){
            year = 5;
        }
        else if (string.equals("6 - Sixth Year")){
            year = 6;
        }
        else if (string.equals("7 - Seventh Year")){
            year = 7;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean filled_in(){
        if (name.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (code.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (school.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.getText().toString().length() < 6){
            Toast.makeText(this, "Password is too short", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (c_password.getText().toString().length() < 6){
            Toast.makeText(this, "Password is too short", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.getText().toString().equals(c_password.getText().toString())){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void adding_to_databasa(){
        Map<String, String> map = new HashMap<>();
        map.put("course_name", name.getText().toString());
        map.put("course_code", code.getText().toString());
        map.put("school", school.getText().toString());
        map.put("faculty", faculty.getText().toString());
        map.put("course_password", password.getText().toString());
        map.put("employee_number", userNumber);
        map.put("course_year", year.toString());

        DataBase.create_course(this,map);
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
            case R.id.profile:
                Intent search = new Intent(this, profile.class);
                search.putExtra("usernumber",userNumber);
                search.putExtra("has_image", "ggh");
                startActivity(search);
                return true;
            case R.id.menu_page:
                DataBase.back_to_menu(create_course.this,userNumber);
                return true;

            case R.id.create:
                drawerLayout.closeDrawer(GravityCompat.START);
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