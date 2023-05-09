package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView name;
    TextView surname;
    TextView email;
    TextView userNumber;
    String user;
    ImageView userImage;
    Intent Image;
    String has = "";
    Bitmap bitmap;
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;

    private final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Intent user_number = getIntent();
        user = user_number.getStringExtra("usernumber");
        name = findViewById(R.id.profile_name);
        surname = findViewById(R.id.profile_surname);
        email = findViewById(R.id.profile_email);
        userNumber = findViewById(R.id.profile_number);
        userImage = (ImageView)findViewById(R.id.profile_image);

        DataBase.get_image(this, user, userImage);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent access_gallary = new Intent(Intent.ACTION_PICK);
                access_gallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(access_gallary, GALLERY_REQ_CODE);

            }
        });

        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);

        TextView userName = view.findViewById(R.id.name);
        userName.setText(user);
        ImageView imageView = view.findViewById(R.id.imageView9);


        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar


        if (user_number.getStringExtra("has_image").isEmpty()){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.student_toolbox);
        }
        else{
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.teacher_toolbox);
        }

        DataBase.profile(this, user, name, surname, email, userNumber);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Profile");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == GALLERY_REQ_CODE){
                Image = data;
                has = "yes";
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    userImage.setImageBitmap(bitmap);
                    add_image();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public void editprofile(View view) {
//        Intent intent = new Intent(this, edit_profile.class);
//        intent.putExtra("userNumber", user);
//        intent.putExtra("email", email.getText().toString());
//        intent.putExtra("surname", surname.getText().toString());
//        intent.putExtra("name", name.getText().toString());
//        intent.putExtra("image", Image);
//        intent.putExtra("has_image", has);
//        startActivity(intent);
//    }

    public void back_to_login(View view) {
        DataBase.back_to_menu(this, user);
    }

    private void add_image(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null){
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
            DataBase.upload_image(this, base64Image, user);
            DataBase.get_image(this, user, userImage);

//            userNumber.setText(base64Image);
        }
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
                search.putExtra("usernumber",user);
                startActivity(search);
                return true;
            case R.id.create:
                Intent create = new Intent(this, create_course.class);
                create.putExtra("usernumber",user);
                startActivity(create);
                return true;

            case R.id.logout:
                Intent log = new Intent(this, MainActivity.class);
                startActivity(log);
                return true;

            case R.id.menu_page:
                DataBase.back_to_menu(profile.this, user);
                return true;

            case R.id.profile:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}