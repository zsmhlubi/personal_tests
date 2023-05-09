package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class search_courses extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String userNumber;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;
    ListView listView;

    // Define array adapter for ListView
    ArrayAdapter<String> adapter;

    // Define array List for List View data
    ArrayList<String> mylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_courses);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("usernumber");
        course_list = (LinearLayout) findViewById(R.id.search);

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

        DataBase.get_all_courses(this,userNumber,course_list,"");

        System.out.println(mylist);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
//        ViewsClass.add_layout(2,0,this,mylist,mylist,mylist,course_list,"ashbdbjhsbd");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu with items using MenuInflator
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);

        // Initialise menu item search bar
        // with id and take its object
        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        // attach setOnQueryTextListener
        // to search view defined above
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Override onQueryTextSubmit method which is call when submit query is searched
            @Override
            public boolean onQueryTextSubmit(String query) {
                // If the list contains the search query than filter the adapter
                // using the filter method with the query as its argument

//                if (mylist.contains(query)) {
//                    adapter.getFilter().filter(query);
//                }
//                else {
//                    // Search query not found in List View
//                    Toast.makeText(search_courses.this, "Course not found", Toast.LENGTH_LONG).show();
//                }
                return false;
            }

            // This method is overridden to filter the adapter according
            // to a search query when the user is typing search
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                DataBase.get_all_courses(search_courses.this,userNumber,course_list,newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    public static void enter_password(Context context, String course_name, String student_number){

        View origin = View.inflate(context,R.layout.search_courses, null);

        View pop_password = View.inflate(context , R.layout.enrrollment_password, null);
        final PopupWindow pop = new PopupWindow(pop_password,1000,400, true);
        pop.showAtLocation(origin, Gravity.CENTER,0,0);

        EditText password = pop_password.findViewById(R.id.course_password);
        TextView cancel = pop_password.findViewById(R.id.cancel);
        TextView enroll = pop_password.findViewById(R.id.enroll);
        TextView massage = pop_password.findViewById(R.id.error);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().isEmpty()){
                    massage.setText("Please enter the password provided by the techer before you can continue");
                }
                else{
                    DataBase.enroll_on(context, course_name, password.getText().toString(), student_number);
                    pop.dismiss();
                }
            }
        });
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
                search.putExtra("has_image","");
                startActivity(search);
                return true;

            case R.id.menu_page:
                DataBase.back_to_menu(search_courses.this,userNumber);
                return true;

            case R.id.search:
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